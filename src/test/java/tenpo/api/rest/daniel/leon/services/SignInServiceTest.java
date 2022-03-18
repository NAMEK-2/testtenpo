package tenpo.api.rest.daniel.leon.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tenpo.api.rest.daniel.leon.dto.SignUpDtoRQ;
import tenpo.api.rest.daniel.leon.dto.SignUpDtoRS;
import tenpo.api.rest.daniel.leon.entities.UserEntity;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.repositories.UserEntityRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignInServiceTest {

    @InjectMocks
    SignInService signInService;

    @Mock
    UserEntityRepository userEntityRepository;

    @Test
    public void testNokDataEmpty(){

        SignUpDtoRQ rq = new SignUpDtoRQ();
        rq.setPassword(null);
        rq.setUserName("Daniel");

        SignUpDtoRS rs = signInService.makeSignIn(rq);

        Assert.assertEquals("Nok Empty Data",
                Long.valueOf(ServiceMessages.ERROR_DATA_MISSING.getCode()),
                Long.valueOf(rs.getStatus()));
    }

    @Test
    public void testNokUserExist(){

        SignUpDtoRQ rq = new SignUpDtoRQ();
        rq.setPassword("Benoit");
        rq.setUserName("Daniel");

        UserEntity user = new UserEntity();
        user.setUserName("Daniel");

        Mockito.when(userEntityRepository.findUserOptByUserName(Mockito.anyString()))
                .thenReturn(Optional.of(user));

        SignUpDtoRS rs = signInService.makeSignIn(rq);

        Assert.assertEquals("Nok User already Exist",
                Long.valueOf(ServiceMessages.ERROR_USER_ALREADY_EXIST.getCode()),
                Long.valueOf(rs.getStatus()));
    }


    @Test
    public void testOk(){

        SignUpDtoRQ rq = new SignUpDtoRQ();
        rq.setPassword("Benoit");
        rq.setUserName("Daniel");

        Mockito.when(userEntityRepository.findUserOptByUserName(Mockito.anyString()))
                .thenReturn(Optional.empty());



        SignUpDtoRS rs = signInService.makeSignIn(rq);

        Assert.assertEquals("Ok User created",
                Long.valueOf(ServiceMessages.SUCCESSFUL_OPERATION.getCode()),
                Long.valueOf(rs.getStatus()));
    }

}
