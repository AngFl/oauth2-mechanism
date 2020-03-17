package club.example.oauth2.server.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class OAuth2ClientDetailCreateModel {

    @NotEmpty(message = "客户端ID")
    private String clientId;

    @NotEmpty(message = "客户端秘钥不能为空")
    private String clientSecret;

    @NotEmpty(message = "资源服务ID不能为空")
    private String resourceIds;

    private String redirectUri;

    @NotEmpty(message = "授权模式不能为空")
    private String grantTypes;

    @NotEmpty(message = "scope 作用域不能为空")
    private String scopes;
}
