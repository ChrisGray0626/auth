package pers.ruizhi.auth;

import pers.ruizhi.auth.domain.User;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
public class Constant {

    // JWT Secret Key
    public static final String SECRET_KEY = "secret";
    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 300;
    public static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 300;
    public static final String ADDITIONAL_INFO_KEY_USER = "user";
    // Auth
    public static final String AUTH_CLIENT = "client1";
    public static final String AUTH_SECRET = "secret";
    public static final String AUTH_RESOURCE_ID = "resource1";
    public static final String AUTH_SCOPE = "all";
    public static final String AUTH_GRANT_PASSWORD = "password";
    public static final String AUTH_GRANT_REFRESH_TOKEN = "refresh_token";
    public static final String LOGIN_KEY_PREFIX = "login:";

    public static final String HEADER_AUTHORIZATION = "Authorization";
    // Temp user detail
    public static final String USERID = "001";
    public static final String USERNAME = "Chris";
    public static final String PASSWORD = "$2a$10$2vgi2N3T5yHqdEHUH101lug07Yd3xJ8YzWgTLJ41H1Z9r8cLfvWw6";
    public static final String EMAIL = "chris@gray.com";

    public static final User USER = new User(USERID, USERNAME, PASSWORD, EMAIL);
}
