package sjy.jiahe.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLockUtil {
    private final static Logger log = LoggerFactory.getLogger(RedisLockUtil.class);

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisLockUtil.redisTemplate = redisTemplate;
    }

    /**
     * 加锁
     * @param key   键
     * @param value 当前时间 + 超时时间
     * @return 是否拿到锁
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);

        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            //是否已被别人抢占
            return StringUtils.isNotEmpty(oldValue) && oldValue.equals(currentValue);
        }
//                //如果锁过期
//        if (StringUtils.isEmpty(currentValue)){
//            return true;
//        }
//        if(Long.parseLong(currentValue) < System.currentTimeMillis()) {
//            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
//            //是否已被别人抢占
//            return oldValue.equals(currentValue);
//        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key   键
     * @param value 当前时间 + 超时时间
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("redis解锁异常");
        }
    }

}
