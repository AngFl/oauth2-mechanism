package club.example.oauth2.server.handler;

import club.example.oauth2.server.entity.web.JsonResponse;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestArgumentExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
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
}
