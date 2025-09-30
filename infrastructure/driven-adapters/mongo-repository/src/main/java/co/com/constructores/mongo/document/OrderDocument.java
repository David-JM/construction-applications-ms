package co.com.constructores.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDocument {
    @Id
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate solicitudeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String constructionType;
    private String status;
}


