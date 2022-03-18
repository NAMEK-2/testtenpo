package tenpo.api.rest.daniel.leon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import tenpo.api.rest.daniel.leon.entities.LogCallApiEntity;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogApiGetDtoRS {

    private Integer status;
    private String message;
    private Page<LogCallApiEntity> result;

}
