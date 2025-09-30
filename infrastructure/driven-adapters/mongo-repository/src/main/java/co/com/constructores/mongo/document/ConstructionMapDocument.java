package co.com.constructores.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "constructionMap")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConstructionMapDocument {
    @Id
    private String id;
    private String[][] grid;
}


