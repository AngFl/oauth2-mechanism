package club.example.oauth2.server;
import club.example.oauth2.server.constant.EnumOAuthUserEnable;
import club.example.oauth2.server.constant.EnumOAuthUserExpired;
import club.example.oauth2.server.constant.EnumOAuthUserLocked;
import club.example.oauth2.server.constant.EnumOAuthUserCredentialExpired;
import java.time.LocalDateTime;

import club.example.oauth2.server.entity.OAuth2UserDetail;
import club.example.oauth2.server.repository.OAuth2UserDetailMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuth2UserDetailMapperTest {

    @Autowired
    private OAuth2UserDetailMapper oAuth2UserDetailMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testOAuthUserStore() {
        OAuth2UserDetail oAuth2UserDetail = new OAuth2UserDetail();
        oAuth2UserDetail.setUsername("metaCo@security.com");
        oAuth2UserDetail.setPassword(passwordEncoder.encode("password"));
        oAuth2UserDetail.setAdditionalInfo("additional");
        oAuth2UserDetail.setAccountExpired(EnumOAuthUserExpired.NON_EXPIRED);
        oAuth2UserDetail.setAccountLocked(EnumOAuthUserLocked.NONE_LOCKED);
        oAuth2UserDetail.setAccountEnable(EnumOAuthUserEnable.ENABLE);
        oAuth2UserDetail.setCredentialExpired(EnumOAuthUserCredentialExpired.NON_EXPIRED);
        oAuth2UserDetail.setCreatedAt(LocalDateTime.now());
        oAuth2UserDetail.setUpdatedAt(LocalDateTime.now());
        oAuth2UserDetail.setMetaFlag(0);

        int insert = oAuth2UserDetailMapper.insert(oAuth2UserDetail);
        Assert.assertNotEquals(0, insert);
    }
}
