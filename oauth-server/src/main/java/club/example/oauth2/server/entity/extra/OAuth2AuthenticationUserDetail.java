package club.example.oauth2.server.entity.extra;

import club.example.oauth2.server.entity.OAuth2UserDetail;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OAuth2AuthenticationUserDetail extends OAuth2UserDetail {

    private int permissionMask;
}
