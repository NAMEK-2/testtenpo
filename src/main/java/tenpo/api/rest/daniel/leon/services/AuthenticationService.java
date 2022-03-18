package tenpo.api.rest.daniel.leon.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.exceptions.ClientException;
import tenpo.api.rest.daniel.leon.helpers.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    UserService userService;

    private final Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String createToken(String username){

        return JWT.create().
                withSubject(username).
                withExpiresAt(new Date(System.currentTimeMillis() + 60*60*1000)).
                sign(algorithm);
    }

    public void verifyToken(String token) throws ClientException {
        String userName = getUserNameFromToken(token);
        String tokenUser = userService.getTokenForUserName(userName);

        if (token.equals(tokenUser)){
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userName, null, null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            throw new ClientException(ServiceMessages.ERROR_TOKEN_INVALID.getCode(),
                    ServiceMessages.ERROR_TOKEN_INVALID.getMessage());
        }
    }

    public String getUserNameFromToken(String token){
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);
        return decoded.getSubject();
    }
}
