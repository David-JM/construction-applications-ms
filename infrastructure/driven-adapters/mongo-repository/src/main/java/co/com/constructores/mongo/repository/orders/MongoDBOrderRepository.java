package co.com.constructores.mongo.repository.orders;

import co.com.constructores.mongo.document.OrderDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface MongoDBOrderRepository extends ReactiveMongoRepository<OrderDocument, String>, ReactiveQueryByExampleExecutor<OrderDocument> {
    Mono<OrderDocument> findFirstByOrderByEndDateDesc();
}
