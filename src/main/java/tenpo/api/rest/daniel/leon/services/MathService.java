package tenpo.api.rest.daniel.leon.services;

import org.springframework.stereotype.Service;
import tenpo.api.rest.daniel.leon.dto.MathSumDto;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;

@Service
public class MathService {

    public MathSumDto sum(Integer numberOne, Integer numberTwo){

        return setResult(numberOne + numberTwo);

    }

    private MathSumDto setResult(Integer sum){
        MathSumDto result = new MathSumDto();
        result.setStatus(ServiceMessages.SUCCESSFUL_OPERATION.getCode());
        result.setMessage(ServiceMessages.SUCCESSFUL_OPERATION.getMessage());
        result.setResult(sum);
        return result;
    }

}
