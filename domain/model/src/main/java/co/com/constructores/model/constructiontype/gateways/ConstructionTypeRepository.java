package co.com.constructores.model.constructiontype.gateways;

import co.com.constructores.model.constructiontype.ConstructionType;
import reactor.core.publisher.Flux;

public interface ConstructionTypeRepository {
    Flux<ConstructionType> getAll();
}
