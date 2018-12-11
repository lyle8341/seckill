package com.lyle.ms.redis;

import com.alibaba.fastjson.JSON;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  /**
   * 获取当个对象
   */
  public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
    //生成真正的key
    String realKey = prefix.getPrefix() + key;
    String str = stringRedisTemplate.opsForValue().get(realKey);
    return stringToBean(str, clazz);
  }

  /**
   * 设置对象
   */
  public <T> boolean set(KeyPrefix prefix, String key, T value) {
    String str = beanToString(value);
    if (str == null || str.length() <= 0) {
      return false;
    }
    //生成真正的key
    String realKey = prefix.getPrefix() + key;
    int seconds = prefix.expireSeconds();
    if (seconds <= 0) {
      stringRedisTemplate.opsForValue().set(realKey, str);
    } else {
      stringRedisTemplate.opsForValue().set(realKey, str, seconds, TimeUnit.SECONDS);
    }
    return true;
  }

  /**
   * 判断key是否存在
   */
  public <T> boolean exists(KeyPrefix prefix, String key) {
    //生成真正的key
    String realKey = prefix.getPrefix() + key;
    return stringRedisTemplate.hasKey(realKey);
  }

  /**
   * 删除
   */
  public boolean delete(KeyPrefix prefix, String key) {
    //生成真正的key
    String realKey = prefix.getPrefix() + key;
    return stringRedisTemplate.delete(realKey);
  }

  public boolean delete(KeyPrefix prefix) {
    if (prefix == null) {
      return false;
    }
    Set<String> keys = scanKeys(prefix.getPrefix());
    if (keys == null || keys.size() <= 0) {
      return true;
    }
    stringRedisTemplate.delete(keys);
    return true;
  }

  public Set<String> scanKeys(String key) {
    Set<String> keys = stringRedisTemplate.keys(key);
    stringRedisTemplate.delete("*" + key + "*");
    return keys;

  }

  /**
   * 增加值
   */
  public <T> Long incr(KeyPrefix prefix, String key) {
    //生成真正的key
    String realKey = prefix.getPrefix() + key;
    return stringRedisTemplate.opsForValue().increment(realKey);
  }

  /**
   * 减少值
   */
  public <T> Long decr(KeyPrefix prefix, String key) {
    //生成真正的key
    String realKey = prefix.getPrefix() + key;
    return stringRedisTemplate.opsForValue().decrement(realKey);
  }

  public static <T> String beanToString(T value) {
    if (value == null) {
      return null;
    }
    Class<?> clazz = value.getClass();
    if (clazz == int.class || clazz == Integer.class) {
      return "" + value;
    } else if (clazz == String.class) {
      return (String) value;
    } else if (clazz == long.class || clazz == Long.class) {
      return "" + value;
    } else {
      return JSON.toJSONString(value);
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T stringToBean(String str, Class<T> clazz) {
    if (str == null || str.length() <= 0 || clazz == null) {
      return null;
    }
    if (clazz == int.class || clazz == Integer.class) {
      return (T) Integer.valueOf(str);
    } else if (clazz == String.class) {
      return (T) str;
    } else if (clazz == long.class || clazz == Long.class) {
      return (T) Long.valueOf(str);
    } else {
      return JSON.toJavaObject(JSON.parseObject(str), clazz);
    }
  }
}
