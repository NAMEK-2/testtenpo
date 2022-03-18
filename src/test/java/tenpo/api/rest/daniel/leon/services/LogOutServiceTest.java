package tenpo.api.rest.daniel.leon.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tenpo.api.rest.daniel.leon.dto.LogOutDtoRS;
import tenpo.api.rest.daniel.leon.entities.UserEntity;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.repositories.UserEntityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogOutServiceTest {

    @InjectMocks
    LogOutService logOutService;

    @Mock
    UserEntityRepository userEntityRepository;

    @Mock
    AuthenticationService authenticationService;

    @Test
    public void testOK(){

        UserEntity user = new UserEntity();
        user.setUserName("Daniel");

        String userName = "Daniel";
        String token = "Bearer XEGDBJEKWLKNM";

        Mockito.when(authenticationService.getUserNameFromToken(Mockito.anyString()))
                .thenReturn(userName);
        Mockito.when(userEntityRepository.findUserByUserName(Mockito.anyString()))
                .thenReturn(user);
        Mockito.when(userEntityRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);

        LogOutDtoRS response = logOutService.logOut(token);

        Assert.assertEquals("Ok LogOut",
                Long.valueOf(ServiceMessages.SUCCESSFUL_OPERATION.getCode()),
                Long.valueOf(response.getStatus()));
    }

}
