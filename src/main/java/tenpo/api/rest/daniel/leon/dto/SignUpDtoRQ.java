package tenpo.api.rest.daniel.leon.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDtoRQ {

    private String userName;
    private String password;
}
