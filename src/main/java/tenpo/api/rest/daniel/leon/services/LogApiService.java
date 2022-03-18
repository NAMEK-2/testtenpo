package tenpo.api.rest.daniel.leon.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tenpo.api.rest.daniel.leon.dto.LogApiGetDtoRS;
import tenpo.api.rest.daniel.leon.entities.LogCallApiEntity;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;
import tenpo.api.rest.daniel.leon.repositories.LogCallApiEntityRepository;

@Service
@Slf4j
public class LogApiService {

    @Autowired
    LogCallApiEntityRepository repository;

    public LogApiGetDtoRS getLogApi(Integer pageNumber, Integer quantity){

        Pageable pageable = PageRequest.of(pageNumber,quantity);
        Page<LogCallApiEntity> page = repository.findAll(pageable);

        return getJsonResponse(page);
    }

    private LogApiGetDtoRS getJsonResponse(Page<LogCallApiEntity> page){

        LogApiGetDtoRS result = new LogApiGetDtoRS();

        result.setMessage(ServiceMessages.SUCCESSFUL_OPERATION.getMessage());
        result.setStatus(ServiceMessages.SUCCESSFUL_OPERATION.getCode());
        result.setResult(page);

        return  result;

    }


}
