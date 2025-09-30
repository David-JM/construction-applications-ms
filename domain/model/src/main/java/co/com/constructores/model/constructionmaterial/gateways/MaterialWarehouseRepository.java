package co.com.constructores.model.constructionmaterial.gateways;

import co.com.constructores.model.constructionmaterial.MaterialWarehouse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaterialWarehouseRepository {

    Mono<MaterialWarehouse> save(MaterialWarehouse materialWarehouse);

    Flux<MaterialWarehouse> getAll();
}
