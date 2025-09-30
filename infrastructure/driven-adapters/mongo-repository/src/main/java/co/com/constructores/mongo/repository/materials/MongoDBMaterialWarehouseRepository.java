package co.com.constructores.mongo.repository.materials;

import co.com.constructores.mongo.document.MaterialWarehouseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBMaterialWarehouseRepository extends ReactiveMongoRepository<MaterialWarehouseDocument, String>, ReactiveQueryByExampleExecutor<MaterialWarehouseDocument> {
}
