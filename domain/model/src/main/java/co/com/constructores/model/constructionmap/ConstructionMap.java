package co.com.constructores.model.constructionmap;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConstructionMap {
    private String[][] grid;
}
