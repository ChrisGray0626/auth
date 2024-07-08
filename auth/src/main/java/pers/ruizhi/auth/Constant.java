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
    public static final Long DEFAULT_EXPIRE_TIME = (long) (1000 * 60 * 60);
    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 300;
    public static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 300;
    public static final String LOGIN_KEY_PREFIX = "login:";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Temp user detail
    public static final String USERID = "001";
    public static final String USERNAME = "Chris";
    public static final String PASSWORD = "$2a$10$2vgi2N3T5yHqdEHUH101lug07Yd3xJ8YzWgTLJ41H1Z9r8cLfvWw6";
    public static final String EMAIL = "chris@gray.com";

    public static final User USER = new User(USERID, USERNAME, PASSWORD, EMAIL);
}
