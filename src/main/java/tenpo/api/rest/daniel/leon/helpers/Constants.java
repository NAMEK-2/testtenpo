package tenpo.api.rest.daniel.leon.helpers;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String BEARER = "Bearer ";
    public static final Map<String, Boolean> ALL_ROUTE_AUTHORIZATION_TOKEN = new HashMap<>();
    public static final String SECRET_KEY= "SECRET_KEY";

    static {
        ALL_ROUTE_AUTHORIZATION_TOKEN.put("/log-api/", false);
        ALL_ROUTE_AUTHORIZATION_TOKEN.put("/math/sum", true);
        ALL_ROUTE_AUTHORIZATION_TOKEN.put("/user/sign-up", false);
        ALL_ROUTE_AUTHORIZATION_TOKEN.put("/user/login", false);
        ALL_ROUTE_AUTHORIZATION_TOKEN.put("/user/log-out", true);
    }


    private Constants(){}

}
