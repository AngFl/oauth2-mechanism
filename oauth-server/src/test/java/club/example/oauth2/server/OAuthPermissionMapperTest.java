package club.example.oauth2.server;
import java.time.LocalDateTime;

import club.example.oauth2.server.constant.OAuth2PermissionUnit;
import club.example.oauth2.server.entity.OAuth2Permission;
import club.example.oauth2.server.repository.OAuth2PermissionMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthPermissionMapperTest {

    @Autowired
    private OAuth2PermissionMapper oAuth2PermissionMapper;

    @Test
    public void testOAuthPermissionStore() {
        OAuth2Permission oAuth2Permission = new OAuth2Permission();
        oAuth2Permission.setPermissionGroupId(1L);
        oAuth2Permission.setPermissionGroupName("admin");
        oAuth2Permission.setPermissionDescription("管理员权限组");
        oAuth2Permission.setPermissionMask(OAuth2PermissionUnit.GOD_MASK);
        oAuth2Permission.setCreatedAt(LocalDateTime.now());
        oAuth2Permission.setUpdatedAt(LocalDateTime.now());
        oAuth2Permission.setMetaFlag(0);

        int insert = oAuth2PermissionMapper.insert(oAuth2Permission);
        Assert.assertNotEquals(0, insert);
    }
}
