package tenpo.api.rest.daniel.leon.interceptors;


import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import tenpo.api.rest.daniel.leon.entities.LogCallApiEntity;
import tenpo.api.rest.daniel.leon.helpers.Constants;
import tenpo.api.rest.daniel.leon.repositories.LogCallApiEntityRepository;
import tenpo.api.rest.daniel.leon.services.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class InterceptorSaveApiUses implements HandlerInterceptor {

    private final LogCallApiEntityRepository repository;
    private final AuthenticationService authenticationService;

    public InterceptorSaveApiUses(LogCallApiEntityRepository repository,
                                  AuthenticationService authenticationService) {
        this.repository = repository;
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        saveLog(getUser(request), request.getServletPath());
        return true;
    }

    private String getUser(HttpServletRequest request) throws IOException {

        String token = request.getHeader(AUTHORIZATION);
        String userName = null;
        try {
            if (token != null && !token.isEmpty() && token.length() > Constants.BEARER.length() ) {
                userName = authenticationService.getUserNameFromToken(token.substring(Constants.BEARER.length()));
            } else {
                String jsonString = IOUtils.toString(request.getInputStream());
                JSONObject json = new JSONObject(jsonString);
                userName = json.getString("userName");
            }
        } catch (Exception ex){
            // Ignore
        }
        return userName;
    }

    private void saveLog(String userName, String route){
        LogCallApiEntity logApi = new LogCallApiEntity();
        logApi.setUserName(userName);
        logApi.setApiRoute(route);
        repository.save(logApi);
    }

}
