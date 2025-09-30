package co.com.constructores.mongo.repository.orders;

import co.com.constructores.model.order.Order;
import co.com.constructores.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final MongoRepositoryOrderAdapter dao;
    private final MongoDBOrderRepository repository;

    @Override
    public Mono<Order> getLastOrder() {
        return dao.doQuery(repository.findFirstByOrderByEndDateDesc());
    }

    @Override
    public Mono<Order> save(Order order) {
        return dao.save(order);
    }

    @Override
    public Flux<Order> getAll() {
        return dao.findAll();
    }
}
