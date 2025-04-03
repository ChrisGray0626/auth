package pers.ruizhi.auth;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
public class Constant {

    // JWT Token Config
    public static final String TOKEN_SECRET_KEY = "secret";
    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24;
    public static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24;
    public static final String ADDITIONAL_INFO_KEY_USER = "user";
    // Auth Config
    public static final String AUTH_CLIENT_NAME = "client1";
    public static final String AUTH_SECRET = "secret";
    public static final String AUTH_RESOURCE_ID = "resource1";
    public static final String AUTH_SCOPE = "all";
    public static final String AUTH_GRANT_PASSWORD = "password";
    public static final String AUTH_GRANT_REFRESH_TOKEN = "refresh_token";

}
