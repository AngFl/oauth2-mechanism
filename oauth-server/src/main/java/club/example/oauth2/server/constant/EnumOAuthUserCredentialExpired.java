package club.example.oauth2.server.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum EnumOAuthUserCredentialExpired {

    NON_EXPIRED("0", "未过期", false),
    EXPIRED("1", "过期", true);

    @EnumValue
    private String value;

    private String description;

    private boolean expired;

    EnumOAuthUserCredentialExpired(String value, String description, boolean expired) {
        this.value = value;
        this.description = description;
        this.expired = expired;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
