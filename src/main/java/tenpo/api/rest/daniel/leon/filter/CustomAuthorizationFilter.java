package tenpo.api.rest.daniel.leon.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.helpers.Constants;
import tenpo.api.rest.daniel.leon.helpers.CachedBodyHttpServletRequest;
import tenpo.api.rest.daniel.leon.services.AuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    public CustomAuthorizationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);

        Boolean needAuth = Constants.ALL_ROUTE_AUTHORIZATION_TOKEN.get(request.getServletPath());

        if ( needAuth != null && needAuth ){
            try {
                String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && !authHeader.isEmpty()) {
                    String token = authHeader;
                    authenticationService.verifyToken(token);
                } else {
                    getErrorToken(response,
                            ServiceMessages.ERROR_TOKEN_NOT_PRESENT.getMessage(),
                            ServiceMessages.ERROR_TOKEN_NOT_PRESENT.getCode());
                }
            } catch (Exception ex){
                getErrorToken(response,
                        ServiceMessages.ERROR_OPERATION.getMessage() + ex.getMessage(),
                        ServiceMessages.ERROR_OPERATION.getCode());
            }
        }

        try {
            filterChain.doFilter(cachedBodyHttpServletRequest, response);
        } catch (Exception ex){
            // Ignore
        }
    }

    private void getErrorToken(HttpServletResponse response, String message, Integer status) {

        try {
            response.setStatus(OK.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            Map<String, String> errors = new HashMap<>();
            errors.put("message", message);
            errors.put("status", String.valueOf(status));
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        } catch (Exception ex){
            log.error(ex.getMessage());
        }

    }
}
