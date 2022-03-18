package tenpo.api.rest.daniel.leon.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tenpo.api.rest.daniel.leon.dto.MathSumDto;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathServiceTest {

    @InjectMocks
    MathService mathService;

    @Test
    public void testOk(){
        MathSumDto sum = mathService.sum(2,2);

        Assert.assertEquals("Sum Status Ok", Long.valueOf(sum.getResult()),
                Long.valueOf(sum.getResult()));
    }

}
