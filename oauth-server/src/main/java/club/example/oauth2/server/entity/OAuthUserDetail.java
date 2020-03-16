package club.example.oauth2.server.entity;

import club.example.oauth2.server.constant.EnumOAuthUserCredentialExpired;
import club.example.oauth2.server.constant.EnumOAuthUserEnable;
import club.example.oauth2.server.constant.EnumOAuthUserExpired;
import club.example.oauth2.server.constant.EnumOAuthUserLocked;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class OAuthUserDetail implements UserDetails {

    private long userId;

    private String username;

    private String password;

    private String additionalInfo;

    private EnumOAuthUserExpired accountExpired;

    private EnumOAuthUserLocked accountLocked;

    private EnumOAuthUserEnable accountEnable;

    private EnumOAuthUserCredentialExpired credentialExpired;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int authoritiesMask;

    private int metaFlag;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !(accountExpired.isExpired());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !(accountLocked.isLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !(credentialExpired.isExpired());
    }

    @Override
    public boolean isEnabled() {
        return accountEnable.isEnable();
    }
}
