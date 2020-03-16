package club.example.oauth2.server.repository;

import club.example.oauth2.server.entity.extra.OAuth2AuthenticationUserDetail;
import org.apache.ibatis.annotations.Param;

public interface AuthenticationUserDetailMapper {

    OAuth2AuthenticationUserDetail findByUsername(@Param("username") String username);
}
