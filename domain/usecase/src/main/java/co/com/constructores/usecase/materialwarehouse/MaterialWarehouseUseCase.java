package co.com.constructores.usecase.materialwarehouse;

import co.com.constructores.model.constructionmaterial.MaterialWarehouse;
import co.com.constructores.model.constructionmaterial.gateways.MaterialWarehouseRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MaterialWarehouseUseCase {
    private final MaterialWarehouseRepository materialWarehouseRepository;

    public Mono<MaterialWarehouse> getMaterials() {
        return materialWarehouseRepository.getAll().next();
    }

    public Mono<MaterialWarehouse> saveMaterials(MaterialWarehouse materialWarehouse) {
        return materialWarehouseRepository.save(materialWarehouse);
    }
}
