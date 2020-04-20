package club.example.oauth2.server;

import club.example.platform.core.AuthorizationUser;
import club.example.platform.service.WeChatPlatformCoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeChatPlatformStarterTest {

    @Autowired
    private WeChatPlatformCoreService weChatPlatformCoreService;

    @Test
    public void testAuthorization() {
        AuthorizationUser authorizationUser = weChatPlatformCoreService
                .authorization("061cyrWf1cYPLt0CcvWf1v4fWf1cyrWV");
        Assert.assertNotNull(authorizationUser);
    }
}
