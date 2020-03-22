package club.example.oauth2.server.constant;

import lombok.Getter;

@Getter
public enum EnumOAuthGrantType {
    AUTHORIZATION_CODE("authorization_code", "授权码模式"),
    PASSWORD("password", "密码模式"),
    AUTHORIZATION_CODE_REFRESH("authorization_code,refresh_token",  "带有刷新令牌的授权码模式"),
    PASSWORD_REFRESH("password,refresh_token", "带有刷新令牌的密码模式"),
    ALL("authorization_code,password,refresh_token", "All supported");

    private String value;

    private String description;

    EnumOAuthGrantType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static EnumOAuthGrantType ofType(String grantType) {
        for (EnumOAuthGrantType enumOAuthGrantType : values()) {
            if (enumOAuthGrantType.value.equals(grantType)) {
                return enumOAuthGrantType;
            }
        }
        return null;
    }
}
