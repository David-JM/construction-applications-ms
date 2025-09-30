package co.com.constructores.mongo.repository.constructiontypes;

import co.com.constructores.model.constructiontype.ConstructionType;
import co.com.constructores.model.constructiontype.gateways.ConstructionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ConstructionTypeRepositoryImpl implements ConstructionTypeRepository {

    private final MongoRepositoryConstructionTypeAdapter dao;

    @Override
    public Flux<ConstructionType> getAll() {
        return dao.findAll();
    }
}
