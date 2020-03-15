package club.example.oauth2.app.security.service.impl;

import club.example.oauth2.app.security.service.PermissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Log4j2
@Service
public class PermissionServiceImpl implements PermissionService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 查询数据库/ redis, 判断用户当前权限
        log.info("requestURI = {}", request.getRequestURI());
        try {
            log.info("{}", objectMapper.writeValueAsString(authentication));
            SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
            return instanceStrong.nextInt() % 2 == 0;
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
