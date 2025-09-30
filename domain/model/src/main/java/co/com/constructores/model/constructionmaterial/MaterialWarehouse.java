package co.com.constructores.model.constructionmaterial;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MaterialWarehouse {
    private String id;
    private Integer adobe;
    private Integer sand;
    private Integer cement;
    private Integer gravel;
    private Integer wood;
}
