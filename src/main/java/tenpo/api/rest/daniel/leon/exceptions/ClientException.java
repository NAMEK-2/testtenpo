package tenpo.api.rest.daniel.leon.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientException extends Exception{

    private int code;

    public ClientException(int code, String message) {
        super(message);
        this.code = code;
    }

}
