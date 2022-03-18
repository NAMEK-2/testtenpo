package tenpo.api.rest.daniel.leon.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tenpo.api.rest.daniel.leon.dto.MathSumDto;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.services.MathService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@Validated
@RequestMapping(value = "/math")
@Slf4j
public class MathController {

    @Autowired
    MathService mathService;

    @GetMapping(value="/sum")
    public ResponseEntity<MathSumDto> sumValues(
            @RequestParam(value = "numberA") Integer numberOne,
            @RequestParam(value = "numberB") Integer numberTwo,
            @RequestHeader(value = AUTHORIZATION, required = true) String idToken
    ){
        MathSumDto result = new MathSumDto();
        try {
            result = mathService.sum(numberOne, numberTwo);
        } catch (Exception ex){
            result.setStatus(ServiceMessages.ERROR_OPERATION.getCode());
            result.setMessage(ServiceMessages.ERROR_OPERATION.getMessage() +  ex.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
