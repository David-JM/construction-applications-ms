package co.com.constructores.api;

import co.com.constructores.api.config.ErrorHandler;
import co.com.constructores.model.constructionmaterial.MaterialWarehouse;
import co.com.constructores.model.solicitude.Solicitude;
import co.com.constructores.usecase.constructionreports.ConstructionReportsUseCase;
import co.com.constructores.usecase.materialwarehouse.MaterialWarehouseUseCase;
import co.com.constructores.usecase.orders.OrdersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class Handler {
    private final OrdersUseCase ordersUseCase;
    private final MaterialWarehouseUseCase materialWarehouseUseCase;
    private final ConstructionReportsUseCase constructionReportsUseCase;

    public Mono<ServerResponse> listenPOSTOrdersUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Solicitude.class)
                .flatMap(ordersUseCase::addConstructionOrder)
                .flatMap(result -> ServerResponse.ok().bodyValue(result))
                .onErrorResume(ErrorHandler.businessError());
    }

    public Mono<ServerResponse> listenGETReportUseCaseProjectEnd(ServerRequest serverRequest) {
        return ServerResponse.ok().body(constructionReportsUseCase.getProjectEndDate(), LocalDate.class);
    }

    public Mono<ServerResponse> listenGETMaterialWarehouseUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().body(materialWarehouseUseCase.getMaterials(), MaterialWarehouse.class);
    }
}
