package tenpo.api.rest.daniel.leon.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tenpo.api.rest.daniel.leon.dto.LoginDtoRQ;
import tenpo.api.rest.daniel.leon.dto.LoginDtoRS;
import tenpo.api.rest.daniel.leon.entities.UserEntity;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.repositories.UserEntityRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServicesTest {

    @InjectMocks
    LoginServices loginServices;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    UserEntityRepository repository;

    @Test
    public void testNokUserOrPasswordWrong(){

        LoginDtoRQ rq = new LoginDtoRQ();
        rq.setPassword("password");
        rq.setUserName("Daniel");

        Mockito.when(repository.findUserOptByUserNameAndPassword(rq.getUserName(),
                rq.getPassword())).thenReturn(Optional.empty());

        LoginDtoRS response = loginServices.login(rq);

        Assert.assertEquals("User or password Wrong",
                Long.valueOf(ServiceMessages.ERROR_DATA_LOGIN.getCode()),
                Long.valueOf(response.getStatus()));

    }

    @Test
    public void testOk(){

        LoginDtoRQ rq = new LoginDtoRQ();
        rq.setPassword("password");
        rq.setUserName("Daniel");

        UserEntity user = new UserEntity();
        user.setUserName("Daniel");
        user.setPassword("password");

        Mockito.when(repository.findUserOptByUserNameAndPassword(rq.getUserName(),
                rq.getPassword())).thenReturn(Optional.of(user));
        Mockito.when(authenticationService.createToken(user.getUserName()))
                .thenReturn("XXXGFHG");
        Mockito.when(repository.findUserByUserName(user.getUserName()))
                .thenReturn(user);
        Mockito.when(repository.save(user))
                .thenReturn(user);

        LoginDtoRS response = loginServices.login(rq);

        Assert.assertEquals("User Login Ok",
                Long.valueOf(ServiceMessages.SUCCESSFUL_OPERATION.getCode()),
                Long.valueOf(response.getStatus()));

    }

}
