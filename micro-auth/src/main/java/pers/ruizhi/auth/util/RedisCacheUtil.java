package pers.ruizhi.auth.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/4
 */
@Component
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class RedisCacheUtil {

    @Resource
    private RedisTemplate redisTemplate;

    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void set(String key, T value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public long getExpireTime(String key) {
        Long expireTime = redisTemplate.getExpire(key);
        if (ObjectUtils.isEmpty(expireTime)) {
            return -2;
        }
        return expireTime;
    }

    public boolean isExpired(String key) {
        return this.getExpireTime(key) < 1;
    }
}
