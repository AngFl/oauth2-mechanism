package club.example.oauth2.server.handler;

import club.example.oauth2.server.entity.web.JsonResponse;
import club.example.oauth2.server.exception.ClientDetailNotFoundException;
import club.example.oauth2.server.exception.ClientScopeNotSupportedException;
import club.example.oauth2.server.exception.GrantTypeNotSupportedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Log4j2
@ControllerAdvice
public class DefaultPageExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> processingArgumentError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            if (StringUtils.hasLength(fieldError.getDefaultMessage())) {
                String errorMessage = String.format("【%s】%s",
                        fieldError.getField(), fieldError.getDefaultMessage());
                return new JsonResponse<>(-10010, errorMessage);
            }
        }

        return new JsonResponse<>(-10001, "Unknown error missing");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("handleIllegalArgument : exception {}",e.toString());
        return new JsonResponse<>(-10011, e.getMessage());
    }

    @ExceptionHandler(value = ClientDetailNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> handleNotFoundClient(ClientDetailNotFoundException e) {
        log.warn("handleNotFoundClient : exception {}",e.toString());
        return new JsonResponse<>(-11, e.getMessage());
    }

    @ExceptionHandler(value = GrantTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> handleGrantTypeNotSupported(GrantTypeNotSupportedException e) {
        log.warn("handleGrantTypeNotSupported : exception {}",e.toString());
        return new JsonResponse<>(-11, e.getMessage());
    }

    @ExceptionHandler(value = ClientScopeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> handleClientScopeNotSupported(ClientScopeNotSupportedException e) {
        log.warn("handleNotFoundClient : exception {}",e.toString());
        return new JsonResponse<>(-11, e.getMessage());
    }
}
