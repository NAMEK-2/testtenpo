package tenpo.api.rest.daniel.leon.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenpo.api.rest.daniel.leon.dto.SignUpDtoRQ;
import tenpo.api.rest.daniel.leon.dto.SignUpDtoRS;
import tenpo.api.rest.daniel.leon.entities.UserEntity;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.repositories.UserEntityRepository;

@Service
@Slf4j
public class SignInService {

    @Autowired
    UserEntityRepository repository;

    public SignUpDtoRS makeSignIn(SignUpDtoRQ rq){

        try{

            if (!verifyData(rq)){
                return setResponse(ServiceMessages.ERROR_DATA_MISSING.getMessage(),
                        ServiceMessages.ERROR_DATA_MISSING.getCode());
            }

            // Verify User is not already in the DB
            if (verifyUserExist(rq.getUserName())){
                return setResponse(ServiceMessages.ERROR_USER_ALREADY_EXIST.getMessage(),
                        ServiceMessages.ERROR_USER_ALREADY_EXIST.getCode());
            }

            //Create the User in the DB
            createUser(rq);

            return setResponse(ServiceMessages.SUCCESSFUL_OPERATION.getMessage(),
                    ServiceMessages.SUCCESSFUL_OPERATION.getCode());

        } catch (Exception ex){
            return setResponse(ServiceMessages.ERROR_OPERATION.getMessage() +  ex.getMessage(),
                    ServiceMessages.ERROR_OPERATION.getCode());
        }
    }

    private boolean verifyUserExist(String userName){
        return repository.findUserOptByUserName(userName).isPresent();
    }

    private void createUser(SignUpDtoRQ rq){
        UserEntity user = new UserEntity();
        user.setPassword(rq.getPassword());
        user.setUserName(rq.getUserName());

        repository.save(user);
    }

    private boolean verifyData(SignUpDtoRQ rq){
        return rq.getPassword() != null && !rq.getPassword().isEmpty()
                && rq.getUserName() != null && !rq.getUserName().isEmpty();
    }

    private SignUpDtoRS setResponse(String message, Integer code){
        return new SignUpDtoRS(message, code);
    }

}
