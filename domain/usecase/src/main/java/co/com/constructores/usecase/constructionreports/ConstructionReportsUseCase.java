package co.com.constructores.usecase.constructionreports;

import co.com.constructores.model.order.Order;
import co.com.constructores.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class ConstructionReportsUseCase {
    private final OrderRepository orderRepository;

    public Mono<LocalDate> getProjectEndDate() {
        return orderRepository.getLastOrder().map(Order::getEndDate);
    }

    public getStateOrders() {
        return null;
    }
}
