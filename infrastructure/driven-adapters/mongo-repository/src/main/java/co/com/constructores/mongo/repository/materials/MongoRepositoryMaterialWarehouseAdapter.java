package co.com.constructores.mongo.repository.materials;

import co.com.constructores.model.constructionmaterial.MaterialWarehouse;
import co.com.constructores.mongo.document.MaterialWarehouseDocument;
import co.com.constructores.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryMaterialWarehouseAdapter extends AdapterOperations<MaterialWarehouse, MaterialWarehouseDocument, String, MongoDBMaterialWarehouseRepository> {

    public MongoRepositoryMaterialWarehouseAdapter(MongoDBMaterialWarehouseRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, MaterialWarehouse.class));
    }
}
