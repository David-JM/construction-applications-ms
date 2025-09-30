package co.com.constructores.mongo.repository.orders;

import co.com.constructores.mongo.document.OrderDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MongoDBOrderRepository extends ReactiveMongoRepository<OrderDocument, String>, ReactiveQueryByExampleExecutor<OrderDocument> {
    Mono<OrderDocument> findFirstByOrderByEndDateDesc();
    Flux<OrderDocument> findByStatus(String status);
    Mono<Long> countByStatus(String status);
}
