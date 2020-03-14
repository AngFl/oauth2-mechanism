package club.example.oauth2.server.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@ JsonIgnoreProperties({"clientSecret", "metaFlag"})
@TableName("oauth_client_details")
public class OAuthClientDetail {

    @TableId
    private String clientId;

    private String clientSecret;

    private String resourceIds;

    @TableField("server_redirect_uri")
    private String redirectUri;

    private String grantTypes;

    private String scopes;

    private EnumAutoApprove autoApprove;

    @TableField("access_token_validity")
    private int accessTokenValiditySeconds;

    @TableField("refresh_token_validity")
    private int refreshTokenValiditySeconds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private int metaFlag;

    public enum EnumAutoApprove {

        DISABLE_AUTO("0", false),
        ENABLE_AUTO("1", true);

        @EnumValue
        private String value;

        private boolean approve;

        EnumAutoApprove(String value, boolean approve) {
            this.value = value;
            this.approve = approve;
        }

        public String getValue() {
            return value;
        }

        public boolean isApprove() {
            return approve;
        }
    }
}
