package co.com.constructores.api.config;

import co.com.constructores.model.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

public class ErrorHandler {
    public static <T> Function<Throwable, Mono<ServerResponse>> businessError() {
        return throwable -> {
            if (throwable instanceof BusinessException ex) {
                return ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of(
                                "error", ex.getMessage(),
                                "code", ex.getErrorCode()
                        ));
            }
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue(Map.of("error", "Ocurrio un error al crear la orden de construccion solicitada"));
        };
    }
}
