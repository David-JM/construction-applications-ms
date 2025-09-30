package co.com.constructores.mongo.repository.orders;

import co.com.constructores.model.order.Order;
import co.com.constructores.mongo.document.OrderDocument;
import co.com.constructores.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryOrderAdapter extends AdapterOperations<Order, OrderDocument, String, MongoDBOrderRepository> {

    public MongoRepositoryOrderAdapter(MongoDBOrderRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Order.class));
    }
}
