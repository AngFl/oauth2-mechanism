package club.example.oauth2.server.handler;

import club.example.oauth2.server.entity.web.JsonResponse;
import club.example.oauth2.server.exception.OAuthClientDetailNotFoundException;
import club.example.oauth2.server.exception.OAuthClientScopeNotSupportedException;
import club.example.oauth2.server.exception.OAuthGrantTypeNotSupportedException;
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
    public JsonResponse<String> processingIllegalArgument(IllegalArgumentException e) {
        return new JsonResponse<>(-10011, e.getMessage());
    }

    @ExceptionHandler(value = OAuthClientDetailNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> processingNotFoundClient(OAuthClientDetailNotFoundException e) {
        log.warn("processingNotFoundClient : exception {}",e.toString());
        return new JsonResponse<>(-11, e.getMessage());
    }

    @ExceptionHandler(value = OAuthGrantTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> processingGrantTypeNotSupported(OAuthGrantTypeNotSupportedException e) {
        log.warn("processingGrantTypeNotSupported : exception {}",e.toString());
        return new JsonResponse<>(-11, e.getMessage());
    }

    @ExceptionHandler(value = OAuthClientScopeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResponse<String> processingClientScopeNotSupported(OAuthClientScopeNotSupportedException e) {
        log.warn("processingNotFoundClient : exception {}",e.toString());
        return new JsonResponse<>(-11, e.getMessage());
    }
}
