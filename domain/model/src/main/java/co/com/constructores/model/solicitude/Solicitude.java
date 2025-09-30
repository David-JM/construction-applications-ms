package co.com.constructores.model.solicitude;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Solicitude {
    private String constructionType;
    private Short coordX;
    private Short coordY;
}
