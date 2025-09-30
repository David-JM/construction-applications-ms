package co.com.constructores.mongo.repository.constructiontypes;

import co.com.constructores.mongo.document.ConstructionTypeDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBConstructionTypeRepository extends ReactiveMongoRepository<ConstructionTypeDocument, String>, ReactiveQueryByExampleExecutor<ConstructionTypeDocument> {
}
