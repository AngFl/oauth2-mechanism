package club.example.oauth2.server;
import java.time.LocalDateTime;


import club.example.oauth2.server.entity.OAuth2PermissionRole;
import club.example.oauth2.server.repository.OAuth2PermissionRoleMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthPermissionRoleMapperTest {

    @Autowired
    private OAuth2PermissionRoleMapper oAuth2PermissionRoleMapper;

    @Test
    public void testPermissionRoleStore() {
        OAuth2PermissionRole oAuth2PermissionRole = new OAuth2PermissionRole();
        oAuth2PermissionRole.setPermissionDescription("管理员");
        oAuth2PermissionRole.setPermissionMask(255);
        oAuth2PermissionRole.setCreatedAt(LocalDateTime.now());
        oAuth2PermissionRole.setUpdatedAt(LocalDateTime.now());
        oAuth2PermissionRole.setMetaFlag(0);

        int insert = oAuth2PermissionRoleMapper.insert(oAuth2PermissionRole);
        Assert.assertNotEquals(0, insert);
    }
}
