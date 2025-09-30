package co.com.constructores.mongo.repository.constructiontypes;

import co.com.constructores.model.constructiontype.ConstructionType;
import co.com.constructores.mongo.document.ConstructionTypeDocument;
import co.com.constructores.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryConstructionTypeAdapter extends AdapterOperations<ConstructionType, ConstructionTypeDocument, String, MongoDBConstructionTypeRepository> {

    public MongoRepositoryConstructionTypeAdapter(MongoDBConstructionTypeRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, ConstructionType.class));
    }
}
