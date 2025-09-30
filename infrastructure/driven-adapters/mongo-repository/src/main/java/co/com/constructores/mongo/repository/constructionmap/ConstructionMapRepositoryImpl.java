package co.com.constructores.mongo.repository.constructionmap;

import co.com.constructores.model.constructionmap.ConstructionMap;
import co.com.constructores.model.constructionmap.gateways.ConstructionMapRepository;
import co.com.constructores.mongo.document.ConstructionMapDocument;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConstructionMapRepositoryImpl implements ConstructionMapRepository {

    private final ObjectMapper mapper;
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<ConstructionMap> addConstruction(Short x, Short y, String constructionType) {
        String fieldPath = String.format("grid.%d.%d", x, y);
        Query query = new Query(Criteria.where(fieldPath).is(""));
        Update update = new Update().set(fieldPath, constructionType);

        return mongoTemplate.findAndModify(query, update, ConstructionMapDocument.class).map(this::toEntity);
    }

    private ConstructionMap toEntity(ConstructionMapDocument data) {
        return mapper.map(data, ConstructionMap.class);
    }
}
