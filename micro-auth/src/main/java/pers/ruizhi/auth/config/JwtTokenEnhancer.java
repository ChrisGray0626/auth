package pers.ruizhi.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import pers.ruizhi.auth.Constant;
import pers.ruizhi.auth.domain.UserDetail;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/12
 */
@Component
@Slf4j
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetail userDetail = (UserDetail) authentication
                .getUserAuthentication()
                .getPrincipal();
        Map<String, Object> info = new HashMap<>();
        info.put(Constant.ADDITIONAL_INFO_KEY_USER, userDetail.getUser());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
