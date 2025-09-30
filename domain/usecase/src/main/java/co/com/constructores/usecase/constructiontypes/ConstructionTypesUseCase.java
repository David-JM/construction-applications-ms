package co.com.constructores.usecase.constructiontypes;

import co.com.constructores.model.constructiontype.ConstructionType;
import co.com.constructores.model.constructiontype.gateways.ConstructionTypeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ConstructionTypesUseCase {
    private final ConstructionTypeRepository constructionTypeRepository;

    public Flux<ConstructionType> getConstructionTypes() {
        return constructionTypeRepository.getAll();
    }
}
