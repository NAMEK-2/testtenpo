package tenpo.api.rest.daniel.leon.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tenpo.api.rest.daniel.leon.dto.LogOutDtoRS;
import tenpo.api.rest.daniel.leon.dto.LoginDtoRQ;
import tenpo.api.rest.daniel.leon.dto.LoginDtoRS;
import tenpo.api.rest.daniel.leon.dto.SignUpDtoRQ;
import tenpo.api.rest.daniel.leon.dto.SignUpDtoRS;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.services.LogOutService;
import tenpo.api.rest.daniel.leon.services.LoginServices;
import tenpo.api.rest.daniel.leon.services.SignInService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@Validated
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    SignInService signInService;

    @Autowired
    LoginServices loginServices;

    @Autowired
    LogOutService logOutService;

    @PostMapping(value = "/sign-up", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SignUpDtoRS> signUp(@RequestBody SignUpDtoRQ rq )
    {
        SignUpDtoRS response = new SignUpDtoRS();

        try {
            response = signInService.makeSignIn(rq);
        } catch (Exception ex){
            response.setMessage(ServiceMessages.ERROR_OPERATION.getMessage() + ex.getMessage());
            response.setStatus(ServiceMessages.ERROR_OPERATION.getCode());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginDtoRS> login(@RequestBody LoginDtoRQ rq )
    {

        LoginDtoRS response = new LoginDtoRS();

        try {
            response = loginServices.login(rq);
        } catch (Exception ex){
           response.setMessage(ServiceMessages.ERROR_OPERATION.getMessage() + ex.getMessage());
           response.setStatus(ServiceMessages.ERROR_OPERATION.getCode());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/log-out" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LogOutDtoRS> logOut(
            @RequestHeader(value = AUTHORIZATION, required = true) String idToken
    ){
        LogOutDtoRS response = new LogOutDtoRS();
        try{
            response = logOutService.logOut(idToken);
        } catch (Exception ex){
            response.setStatus(ServiceMessages.ERROR_OPERATION.getCode());
            response.setMessage(ServiceMessages.ERROR_OPERATION.getMessage() + ex.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
