package tenpo.api.rest.daniel.leon.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tenpo.api.rest.daniel.leon.dto.LogApiGetDtoRS;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.services.LogApiService;

@RestController
@Validated
@RequestMapping(value = "/log-api")
@Slf4j
public class LogApiController {

    @Autowired
    LogApiService logApiService;


    @GetMapping(value="")
    public ResponseEntity<LogApiGetDtoRS> getLogPaginate(
            @RequestParam(value = "pageNumber") Integer pageNumber,
            @RequestParam(value = "quantity") Integer quantity
    ){

        LogApiGetDtoRS result = new LogApiGetDtoRS();
        try {
            result = logApiService.getLogApi(pageNumber, quantity);
        } catch (Exception ex){
            result.setStatus(ServiceMessages.ERROR_OPERATION.getCode());
            result.setMessage(ServiceMessages.ERROR_OPERATION.getMessage() + ex.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
