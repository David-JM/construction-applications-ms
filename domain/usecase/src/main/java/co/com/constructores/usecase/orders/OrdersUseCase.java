package co.com.constructores.usecase.orders;

import co.com.constructores.model.constructionmap.gateways.ConstructionMapRepository;
import co.com.constructores.model.constructionmaterial.MaterialWarehouse;
import co.com.constructores.model.constructiontype.ConstructionType;
import co.com.constructores.model.exceptions.BusinessException;
import co.com.constructores.model.exceptions.ErrorCode;
import co.com.constructores.model.exceptions.ErrorMessages;
import co.com.constructores.model.order.Order;
import co.com.constructores.model.order.gateways.OrderRepository;
import co.com.constructores.model.solicitude.Solicitude;
import co.com.constructores.usecase.constructiontypes.ConstructionTypesUseCase;
import co.com.constructores.usecase.materialwarehouse.MaterialWarehouseUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class OrdersUseCase {
    private final MaterialWarehouseUseCase materialWarehouseUseCase;
    private final ConstructionTypesUseCase constructionTypesUseCase;

    private final ConstructionMapRepository constructionMapRepository;
    private final OrderRepository orderRepository;

    private static final int ONE_DAY = 1;

    public Mono<Order> addConstructionOrder(Solicitude solicitude) {
        var constructionTypeMono = constructionTypesUseCase.getConstructionTypes().filter(res -> res.getType().equals(solicitude.getConstructionType())).next();

        return constructionTypeMono.flatMap(constructionType -> materialWarehouseUseCase.getMaterials()
                .flatMap(material -> validateAvailableMaterials(material, constructionType)
                        .flatMap(remainingMaterial -> constructionMapRepository.addConstruction(solicitude.getCoordX(), solicitude.getCoordY(), solicitude.getConstructionType())
                                .switchIfEmpty(Mono.error(new BusinessException(ErrorMessages.OCCUPIED_COORDINATES, ErrorCode.OCCUPIED_COORDS)))
                                .flatMap(r -> createOrder(constructionType).flatMap(order -> materialWarehouseUseCase.saveMaterials(remainingMaterial).thenReturn(order)))
                        )
                )
        );
    }

    private Mono<MaterialWarehouse> validateAvailableMaterials(MaterialWarehouse materialWarehouse, ConstructionType constructionType) {
        var cementRemains = materialWarehouse.getCement() - constructionType.getCement();
        var sandRemains = materialWarehouse.getSand() - constructionType.getSand();
        var gravelRemains = materialWarehouse.getGravel() - constructionType.getGravel();
        var woodRemains = materialWarehouse.getWood() - constructionType.getWood();
        var adobeRemains = materialWarehouse.getAdobe() - constructionType.getAdobe();

        materialWarehouse.setCement(cementRemains);
        materialWarehouse.setSand(sandRemains);
        materialWarehouse.setGravel(gravelRemains);
        materialWarehouse.setWood(woodRemains);
        materialWarehouse.setAdobe(adobeRemains);

        return cementRemains < 0 || sandRemains < 0 || gravelRemains < 0 || woodRemains < 0 || adobeRemains < 0 ?
                Mono.error((new BusinessException(ErrorMessages.INSUFFICIENT_MATERIALS, ErrorCode.NO_MATERIALS))) :
                Mono.just(materialWarehouse);
    }

    private Mono<Order> createOrder(ConstructionType constructionType) {
        var now = LocalDate.now();
        var initDate = now.plusDays(ONE_DAY);

        var orderBuilder = Order.builder()
                .constructionType(constructionType.getType())
                .solicitudeDate(now)
                .startDate(initDate)
                .endDate(initDate.plusDays(constructionType.getDays() - ONE_DAY)); //To finish on night of last day

        return orderRepository.getLastOrder()
                .map(lastOrder -> {
                    var lastRegisterDay = lastOrder.getEndDate().plusDays(ONE_DAY);
                    return orderBuilder.startDate(lastRegisterDay)
                            .endDate(lastRegisterDay.plusDays(constructionType.getDays() - ONE_DAY))
                            .build();
                })
                .switchIfEmpty(Mono.just(orderBuilder.build()))
                .flatMap(orderRepository::save);
    }
}
