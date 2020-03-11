package club.example.oauth2.server.service;

import club.example.oauth2.server.config.properties.OAuth2SecurityProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RedisAuthorizationCodeService extends RandomValueAuthorizationCodeServices {

    private static final String AUTHORIZATION_CODE_KEY = "authorization:code:";

    private final OAuth2SecurityProperties oAuth2SecurityProperties;

    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * 注意一点，这里存的redis的序列表用默认的JdkSerializationStrategy，跟RedisTokenStore类似
     * 不能用json的，使用json时反序列成token的时候会报错，非要用json的需要同时修改token序列化方式
     */
    private final RedisTokenStoreSerializationStrategy serializationStrategy = new JdkSerializationStrategy();

    @Autowired
    public RedisAuthorizationCodeService(OAuth2SecurityProperties oAuth2SecurityProperties,
                                         RedisConnectionFactory redisConnectionFactory) {
        this.oAuth2SecurityProperties = oAuth2SecurityProperties;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * 授权码存储至 redis 中
     * @param code String
     * @param authentication OAuth2Authentication
     */
    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        log.info("store code = {}, authentication = {}", code, authentication.toString());
        byte[] serializeKey = serializationStrategy.serialize(AUTHORIZATION_CODE_KEY + code);
        byte[] serializedAuthentication = serializationStrategy.serialize(authentication);

        RedisConnection redisConnection = redisConnectionFactory.getConnection();
        redisConnection.openPipeline();
        redisConnection.set(serializeKey, serializedAuthentication);
        redisConnection.expire(serializeKey, oAuth2SecurityProperties
                .getAuthorizationCode().getExpiration());
        redisConnection.closePipeline();

        redisConnection.close();
    }

    /**
     * 取出授权码并删除授权码(权限码只能用一次，调试时可不删除，code就可多次使用)
     *
     * @param code String
     * @return org.springframework.security.oauth2.provider.OAuth2Authentication
     */
    @Override
    protected OAuth2Authentication remove(String code) {
        log.info("remove code = {}", code);
        byte[] serializeKey = serializationStrategy.serialize(AUTHORIZATION_CODE_KEY + code);
        RedisConnection redisConnection = redisConnectionFactory.getConnection();

        byte[] bytes = redisConnection.get(serializeKey);
        if (null != bytes) {
            redisConnection.del(serializeKey);
        }
        redisConnection.close();

        return serializationStrategy.deserialize(bytes, OAuth2Authentication.class);
    }
}
