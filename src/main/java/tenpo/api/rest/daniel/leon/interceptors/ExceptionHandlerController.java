package tenpo.api.rest.daniel.leon.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tenpo.api.rest.daniel.leon.dto.ResponseGenericDto;
import tenpo.api.rest.daniel.leon.enums.ServiceMessages;

import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ExceptionHandlerController extends ResponseEntityExceptionHandler  {


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String bodyOfResponse = getMessage(" Url Request Don't find");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Not Supported Media type");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Missing Param or body");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Path not Found");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Request missing or don't find");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Filter error for request");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Error of type");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Error to read request");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Validation error");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Binding error");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = getMessage("Error no handler for the request");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ex, bodyOfResponse,
                header, HttpStatus.OK, request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAllOthersExceptions(final Exception ex, final WebRequest request) {
        String bodyOfResponse = getMessage(ex.getLocalizedMessage());

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(bodyOfResponse, header, HttpStatus.OK);
    }

    private String getMessage(String message){

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ResponseGenericDto dto = new ResponseGenericDto();
            dto.setMessage(ServiceMessages.ERROR_OPERATION.getMessage() + message);
            dto.setStatus(ServiceMessages.ERROR_OPERATION.getCode());
            return objectMapper.writeValueAsString(dto);
        }
        catch (IOException e) {
            return message;
        }

    }

}
