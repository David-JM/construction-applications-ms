package co.com.constructores.usecase.constructionmap;

import co.com.constructores.model.constructionmap.ConstructionMap;
import co.com.constructores.model.constructionmap.gateways.ConstructionMapRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ConstructionMapUseCase {
    private final ConstructionMapRepository constructionMapRepository;

    public Mono<ConstructionMap> addConstruction(Short x, Short y, String constructionType){
        return constructionMapRepository.addConstruction(x, y, constructionType);
    }

    public Mono<ConstructionMap> findAllConstruccions(){
        return constructionMapRepository.findAllConstruccions().next();
    }

}
