package club.example.oauth2.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@TableName(value = "oauth_permission_roles")
public class OAuth2PermissionRole {

    @TableId
    private Long permissionRoleId;

    private String permissionDescription;
    private int permissionMask;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int metaFlag;
}
