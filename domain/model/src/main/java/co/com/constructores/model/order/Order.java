package co.com.constructores.model.order;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Order {
    private LocalDate solicitudeDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String constructionType;
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;
}
