package co.com.constructores.mongo.repository.materials;

import co.com.constructores.model.constructionmaterial.MaterialWarehouse;
import co.com.constructores.model.constructionmaterial.gateways.MaterialWarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MaterialWarehouseRepositoryImpl implements MaterialWarehouseRepository {

    private final MongoRepositoryMaterialWarehouseAdapter dao;

    @Override
    public Mono<MaterialWarehouse> save(MaterialWarehouse order) {
        return dao.save(order);
    }

    @Override
    public Flux<MaterialWarehouse> getAll() {
        return dao.findAll();
    }
}
