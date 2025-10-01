package co.com.constructores.api;

import co.com.constructores.api.config.ErrorHandler;
import co.com.constructores.model.constructionmap.ConstructionMap;
import co.com.constructores.model.constructiontype.ConstructionType;
import co.com.constructores.model.solicitude.Solicitude;
import co.com.constructores.usecase.constructionmap.ConstructionMapUseCase;
import co.com.constructores.usecase.constructionreports.ConstructionReportsUseCase;
import co.com.constructores.usecase.constructiontypes.ConstructionTypesUseCase;
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
    private final ConstructionMapUseCase constructionMapUseCase;
    private final ConstructionTypesUseCase constructionTypesUseCase;
    private final ConstructionReportsUseCase constructionReportsUseCase;

    public Mono<ServerResponse> listenPOSTOrdersUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Solicitude.class)
                .flatMap(ordersUseCase::addConstructionOrder)
                .flatMap(result -> ServerResponse.ok().bodyValue(result))
                .onErrorResume(ErrorHandler.businessError());
    }

    public Mono<ServerResponse> listenGETConstructionMapUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().body(constructionMapUseCase.findAllConstruccions(), ConstructionMap.class);
    }

    public Mono<ServerResponse> listenGETConstructionTypesUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().body(constructionTypesUseCase.getConstructionTypes(), ConstructionType.class);
    }

    public Mono<ServerResponse> listenGETReportUseCaseProjectEnd(ServerRequest serverRequest) {
        return ServerResponse.ok().body(constructionReportsUseCase.getProjectEndDate(), LocalDate.class);
    }

    public Mono<ServerResponse> listenGETReportUseCaseOrdersState(ServerRequest serverRequest) {
        return constructionReportsUseCase.getOrdersReport()
                .flatMap(ordersReport -> ServerResponse.ok().bodyValue(ordersReport))
                .onErrorResume(ErrorHandler.businessError());
    }
}
