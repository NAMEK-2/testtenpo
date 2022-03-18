package tenpo.api.rest.daniel.leon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDtoRQ {

    private String userName;
    private String password;

}
