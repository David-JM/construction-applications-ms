package co.com.constructores.model.order.gateways;

import co.com.constructores.model.order.Order;
import co.com.constructores.model.order.OrdersReport;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Order> save(Order order);

    Mono<Order> getLastOrder();

    Mono<OrdersReport> getStateOrders();
}
