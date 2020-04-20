package club.example.oauth2.server.constant.oauth;

import lombok.Getter;

@Getter
public enum EnumOAuthClientScope {
    READ("read", "基础查询权限"),
    WRITE("write",  "基础修改权限"),
    ALL("read,write", "全部权限");

    private String value;

    private String description;

    EnumOAuthClientScope(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static EnumOAuthClientScope ofScope(String scope) {
        for (EnumOAuthClientScope e : values()) {
            if (e.value.equals(scope)) {
                return e;
            }
        }
        return null;
    }
}
