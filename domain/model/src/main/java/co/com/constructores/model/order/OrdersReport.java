package co.com.constructores.model.order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrdersReport {
    List<Order> pendingOrders;
    Long inProgressCount;
    Long doneCount;
}
