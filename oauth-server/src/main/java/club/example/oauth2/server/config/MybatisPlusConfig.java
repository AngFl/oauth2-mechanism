package club.example.oauth2.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("club.example.oauth2.server.repository")
public class MybatisPlusConfig {
}
