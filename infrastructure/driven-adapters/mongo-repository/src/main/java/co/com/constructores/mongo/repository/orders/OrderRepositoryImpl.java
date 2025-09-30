package co.com.constructores.mongo.repository.orders;

import co.com.constructores.model.exceptions.BusinessException;
import co.com.constructores.model.exceptions.ErrorCode;
import co.com.constructores.model.exceptions.ErrorMessages;
import co.com.constructores.model.order.Order;
import co.com.constructores.model.order.OrderStatus;
import co.com.constructores.model.order.OrdersReport;
import co.com.constructores.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final MongoRepositoryOrderAdapter dao;
    private final MongoDBOrderRepository repository;

    @Override
    public Mono<Order> save(Order order) {
        return dao.save(order);
    }

    @Override
    public Mono<Order> getLastOrder() {
        return dao.doQuery(repository.findFirstByOrderByEndDateDesc());
    }

    @Override
    public Mono<OrdersReport> getStateOrders() {
        Mono<List<Order>> pendingOrders = dao.doQueryMany(repository.findByStatus(OrderStatus.PENDING.name())).collectList();
        Mono<Long> inProgressCount = repository.countByStatus(OrderStatus.IN_PROGRESS.name());
        Mono<Long> doneCount = repository.countByStatus(OrderStatus.DONE.name());

        return Mono.zip(pendingOrders, inProgressCount, doneCount)
                .switchIfEmpty(Mono.error(new BusinessException(ErrorMessages.ORDERS_REPORT_NO_DATA, ErrorCode.NO_DATA)))
                .map(tuple -> OrdersReport.builder().pendingOrders(tuple.getT1()).inProgressCount(tuple.getT2()).doneCount(tuple.getT3()).build())
                .onErrorMap(ex -> new BusinessException(ErrorMessages.ORDERS_REPORT_FAILED, ErrorCode.REPORT_FAILED));
    }
}
