package tenpo.api.rest.daniel.leon.enums;

import lombok.Getter;
import lombok.Setter;

public enum ServiceMessages {

    SUCCESSFUL_OPERATION		 (0,  "Successful Operation"),
    ERROR_OPERATION			     (-1,  "Error: "),
    ERROR_USER_ALREADY_EXIST     (-2,  "Error: User Already Exist"),
    ERROR_DATA_LOGIN             (-3,  "Error: Password or User Incorrect"),
    ERROR_DATA_MISSING           (-4, "Error: Data is missing in the request"),
    ERROR_TOKEN_NOT_PRESENT      (-9,  "Error: Token is not Present"),
    ERROR_TOKEN_INVALID          (-10,  "Error: Token invalid to the user");

    @Setter
    @Getter
    private int code;
    @Setter
    @Getter
    private String message;

    ServiceMessages(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
