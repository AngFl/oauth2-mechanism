package club.example.oauth2.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@TableName(value = "oauth_permission")
public class OAuth2Permission {

    public static final int PERMISSION_MASK_MAX_OFFSET = 255;

    @TableId
    private Long permissionId;

    private Long permissionGroupId;

    private String permissionGroupName;

    private String permissionDescription;

    private int  permissionMask;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int metaFlag;
}
