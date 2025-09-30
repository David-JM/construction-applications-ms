package co.com.constructores.model.constructionmap.gateways;

import co.com.constructores.model.constructionmap.ConstructionMap;
import reactor.core.publisher.Mono;

public interface ConstructionMapRepository {
    Mono<ConstructionMap> addConstruction(Short x, Short y, String constructionType);
}
