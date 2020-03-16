package club.example.oauth2.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@TableName(value = "oauth_user_permission_role")
public class OAuth2UserPermissionRole {

    @TableId
    private Long oauthUserPermissionId;
    private Long oauthUserId;

    private Long oauthPermissionRoleId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int metaFlag;
}
