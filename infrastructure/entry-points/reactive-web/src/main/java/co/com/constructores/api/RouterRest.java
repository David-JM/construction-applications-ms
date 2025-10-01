package co.com.constructores.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/orders"), handler::listenPOSTOrdersUseCase)
                .andRoute(GET("/api/materials"), handler::listenGETMaterialWarehouseUseCase)
                .andRoute(GET("/api/constructions"), handler::listenGETConstructionMapUseCase)
                .andRoute(GET("/api/construction/types"), handler::listenGETConstructionTypesUseCase)
                .andRoute(GET("/api/reports/project-end"), handler::listenGETReportUseCaseProjectEnd)
                .andRoute(GET("/api/reports/orders-state"), handler::listenGETReportUseCaseOrdersState);
    }
}
