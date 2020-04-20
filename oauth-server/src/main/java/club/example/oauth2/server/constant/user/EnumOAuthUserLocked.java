package club.example.oauth2.server.constant.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum EnumOAuthUserLocked {

    NONE_LOCKED("0", "未锁定", false),
    LOCKED("1", "锁定", true);

    @EnumValue
    private String value;

    private String description;

    private boolean locked;

    EnumOAuthUserLocked(String value, String description, boolean locked) {
        this.value = value;
        this.description = description;
        this.locked = locked;
    }
}
