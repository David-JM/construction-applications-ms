package co.com.constructores.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "constructionTypes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConstructionTypeDocument {
    @Id
    private String id;

    private String type;
    private Integer adobe;
    private Integer sand;
    private Integer cement;
    private Integer gravel;
    private Integer wood;
    private Short days;
}


