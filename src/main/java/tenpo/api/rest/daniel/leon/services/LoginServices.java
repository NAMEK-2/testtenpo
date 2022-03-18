package tenpo.api.rest.daniel.leon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenpo.api.rest.daniel.leon.dto.LoginDtoRQ;
import tenpo.api.rest.daniel.leon.dto.LoginDtoRS;
import tenpo.api.rest.daniel.leon.entities.UserEntity;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.repositories.UserEntityRepository;

@Service
public class LoginServices {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserEntityRepository repository;

    public LoginDtoRS login(LoginDtoRQ rq){

        try {

            //Verify data to login is in the DB
            if (!isOkLogin(rq)){
                return setResponse(ServiceMessages.ERROR_DATA_LOGIN.getMessage(),
                        ServiceMessages.ERROR_DATA_LOGIN.getCode(),
                        null);
            }

            //Get token to return
            String token = getToken(rq.getUserName());

            saveToken(rq.getUserName(), token);

            return setResponse(ServiceMessages.SUCCESSFUL_OPERATION.getMessage(),
                    ServiceMessages.SUCCESSFUL_OPERATION.getCode(),
                    token);

        } catch (Exception ex){
            return setResponse(ServiceMessages.ERROR_OPERATION.getMessage() + ex.getMessage(),
                    ServiceMessages.ERROR_OPERATION.getCode(),
                    null);
        }
    }

    private boolean isOkLogin(LoginDtoRQ rq){
        return repository.findUserOptByUserNameAndPassword(rq.getUserName(),
                rq.getPassword()).isPresent();
    }

    private String getToken(String userName){
        return authenticationService.createToken(userName);
    }

    private void saveToken(String userName, String token){
        UserEntity user = repository.findUserByUserName(userName);
        user.setToken(token);
        repository.save(user);
    }

    private LoginDtoRS setResponse(String message, Integer code, String token){
        return new LoginDtoRS(message, code, token);
    }

}
