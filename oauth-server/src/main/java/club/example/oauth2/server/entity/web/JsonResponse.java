package club.example.oauth2.server.entity.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponse<T> {

    @JsonProperty(value = "error_code")
    private int code;

    @JsonProperty(value = "error_message")
    private String message;

    private T data;

    public JsonResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
