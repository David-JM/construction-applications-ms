package co.com.constructores.model.constructiontype;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConstructionType {
    private String type;
    private Integer adobe;
    private Integer sand;
    private Integer cement;
    private Integer gravel;
    private Integer wood;
    private Integer days;
}
