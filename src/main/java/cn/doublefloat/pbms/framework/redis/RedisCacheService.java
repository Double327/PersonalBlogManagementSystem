package cn.doublefloat.pbms.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author 李广帅
 * @date 2020/7/2 9:21 下午
 */
@Service
public class RedisCacheService {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本对象
     *
     * @param key   键值
     * @param value 值
     * @return 缓存对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return operations;
    }

    /**
     * 设置基本对象
     *
     * @param key      键值
     * @param value    值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 缓存对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(key, value, timeout, timeUnit);
        return operations;
    }

    /**
     * 删除单个对象
     *
     * @param key 键值
     */
    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 集合对象
     */
    public void deleteObject(Collection collection) {
        redisTemplate.delete(collection);
    }

    /**
     * 获取缓存对象
     *
     * @param key 键值
     * @return 值
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
}
