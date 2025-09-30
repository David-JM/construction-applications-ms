package co.com.constructores.model.order.gateways;

import co.com.constructores.model.order.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Order> getLastOrder();

    Mono<Order> save(Order order);

    Flux<Order> getAll();
}
