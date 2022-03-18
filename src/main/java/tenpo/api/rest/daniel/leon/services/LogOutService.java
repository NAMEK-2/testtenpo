package tenpo.api.rest.daniel.leon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenpo.api.rest.daniel.leon.dto.LogOutDtoRS;
import tenpo.api.rest.daniel.leon.entities.UserEntity;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.helpers.Constants;
import tenpo.api.rest.daniel.leon.repositories.UserEntityRepository;

@Service
public class LogOutService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    AuthenticationService authenticationService;

    public LogOutDtoRS logOut(String token){
        try {
            String userName = authenticationService.getUserNameFromToken(
                    token.substring(Constants.BEARER.length()));
            UserEntity user = userEntityRepository.findUserByUserName(userName);
            deleteToken(user);
            return setResponse(ServiceMessages.SUCCESSFUL_OPERATION.getMessage(),
                    ServiceMessages.SUCCESSFUL_OPERATION.getCode());
        } catch (Exception ex){
            return setResponse(ServiceMessages.ERROR_OPERATION.getMessage()
            + ex.getMessage(), ServiceMessages.ERROR_OPERATION.getCode());
        }
    }

    private void deleteToken(UserEntity user){
        user.setToken(null);
        userEntityRepository.save(user);
    }

    private LogOutDtoRS setResponse(String message, Integer code){
        return new LogOutDtoRS(message, code);
    }

}
