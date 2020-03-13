package club.example.oauth2.server;
import java.time.LocalDateTime;
import club.example.oauth2.server.entity.OAuthClientDetail.EnumAutoApprove;

import club.example.oauth2.server.entity.OAuthClientDetail;
import club.example.oauth2.server.repository.OAuthClientDetailMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthClientDetailMapperTest {

    @Autowired
    private OAuthClientDetailMapper oAuthClientDetailMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testOAuthClientCreate() {
        OAuthClientDetail oAuthClientDetail = new OAuthClientDetail();
        oAuthClientDetail.setClientId("client-test-d");
        oAuthClientDetail.setClientSecret(passwordEncoder.encode("123456"));
        oAuthClientDetail.setResourceIds("resourceApp-b");
        oAuthClientDetail.setRedirectUri("");
        oAuthClientDetail.setGrantTypes("refresh_token,password");
        oAuthClientDetail.setScopes("read,write");
        oAuthClientDetail.setAutoApprove(EnumAutoApprove.DISABLE_AUTO);
        oAuthClientDetail.setAccessTokenValiditySeconds(3600);
        oAuthClientDetail.setRefreshTokenValiditySeconds(7200);
        oAuthClientDetail.setCreatedAt(LocalDateTime.now());
        oAuthClientDetail.setUpdatedAt(LocalDateTime.now());
        oAuthClientDetail.setMetaFlag(0);

        int insert = oAuthClientDetailMapper.insert(oAuthClientDetail);
        Assert.assertNotEquals(0, insert);
    }
}
