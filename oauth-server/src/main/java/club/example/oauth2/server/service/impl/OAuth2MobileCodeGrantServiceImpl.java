package club.example.oauth2.server.service.impl;

import club.example.oauth2.server.service.OAuth2MobileCodeGrantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Log4j2
@Service
public class OAuth2MobileCodeGrantServiceImpl implements OAuth2MobileCodeGrantService {

    private final static String MOBILE_CODE_GRANT_KEY = "sms:code:";

    private final StringRedisTemplate stringRedisTemplate;
    private ValueOperations<String, String> stringValueOperations;

    private final RandomValueStringGenerator generator = new RandomValueStringGenerator();

    @Autowired
    public OAuth2MobileCodeGrantServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostConstruct
    public void init() {
        log.info("stringValueOperation init...");
        stringValueOperations = stringRedisTemplate.opsForValue();
        Assert.notNull(stringValueOperations, "stringValueOperations could not be null");
    }

    @Override
    public String sendCode(String mobileNumber) {
        log.info("sendCode mobileNumber = {}", mobileNumber);
        //Get random integer in range
        String value = generator.generate();
        stringValueOperations.setIfAbsent(MOBILE_CODE_GRANT_KEY + mobileNumber,
                value, Duration.ofSeconds(3600));
        return value;
    }

    @Override
    public boolean validate(String mobile, String mobileCode) {
        log.info("validate mobile = {}, mobileCode = {}", mobile, mobileCode);
        String value = stringValueOperations.get(MOBILE_CODE_GRANT_KEY + mobile);
        if (null == value || value.isEmpty()) {
            return false;
        }
        return mobileCode.equals(value);
    }
}
