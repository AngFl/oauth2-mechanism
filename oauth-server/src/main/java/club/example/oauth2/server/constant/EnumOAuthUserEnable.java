package club.example.oauth2.server.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum EnumOAuthUserEnable {

    DISABLE("0", "禁用", false),
    ENABLE("1", "启用", true);

    @EnumValue
    private String value;

    private String description;

    private boolean enable;

    EnumOAuthUserEnable(String value, String description, boolean enable) {
        this.value = value;
        this.description = description;
        this.enable = enable;
    }
}
