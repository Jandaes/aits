package com.aits.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author 作者 jared E-mail:
 * @version 创建时间：2017年5月25日 下午2:40:40 类说明 redis工具包
 */
public class RedisCacheUtils {

    private RedisTemplate<String, String> template;
    private ValueOperations<String, String> valueOperations;
    private SetOperations<String, String> setOperations;
    private ListOperations<String, String> listOperations;
    private HashOperations<String, String, Object> hashOperations;
  
    public RedisCacheUtils() {
        // nothing...
    }
    public RedisCacheUtils(RedisTemplate<String, String> template) {
        this.template=template;
        this.valueOperations = template.opsForValue();
        this.setOperations = template.opsForSet();
        this.listOperations = template.opsForList();
        this.hashOperations = template.opsForHash();
    }
    
    public RedisTemplate<String, String> getTemplate() {
        return template;
    }

    private RedisConnection getConnection() {
        return this.getTemplate().getConnectionFactory().getConnection();

    }

    public void setTemplate(RedisTemplate<String, String> template) {
        this.template = template;
        this.valueOperations = template.opsForValue();
        this.setOperations = template.opsForSet();
        this.listOperations = template.opsForList();
        this.hashOperations = template.opsForHash();
    }
    

    /**
     * 缓存设置入口
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param dataType
     *            数据类型
     * @param timeout
     *            过期时间，当大于0时设置过期时间，过期时间单位默认为秒
     */
    @SuppressWarnings("unchecked")
    public <T> void setCacheObject(String key, T value, DataType dataType,
            long timeout) {

        if (DataType.STRING.equals(dataType)) {
            valueOperations.set(key, (String) value);

        } else if (DataType.LIST.equals(dataType)) {
            listOperations.leftPush(key, (String) value);

        } else if (DataType.SET.equals(dataType)) {
            setOperations.add(key, (String) value);

        } else if (DataType.HASH.equals(dataType)) {
            hashOperations.putAll(key, (Map<String, Object>) value);
        }

        // 设置缓存过期时间
        if (timeout > 0) {
            template.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

     /*
     * @param timeout 过期时间，当大于0时设置过期时间
     */
    private void expire(String key, long timeout) {
        if (timeout > 0) {
            template.expire(key, timeout, TimeUnit.MILLISECONDS);
        }
    }

    public void setString(String key, String value, long timeout) {
        valueOperations.set(key, (String) value);
        expire(key, timeout);
    }

    public void setString(String key, String value) {
        valueOperations.set(key, (String) value);
    }

    public String getString(String key) {
        return valueOperations.get(key);
    }
    public void setList(String key, List<String> value, long timeout) {
        for(String str:value){
            listOperations.leftPush(key, str);
        }
        expire(key, timeout);
    }
    
    public void setList(String key, List<String> value) {
        for(String str:value){
            listOperations.leftPush(key, str);
        }
    }
    
    public List<String> getList(String key) {
        return listOperations.range(key, 0, -1);
    }
    
    public void setSet(String key, Set<String> value, long timeout) {
        for(String str:value){
            setOperations.add(key, str);        
            }
        expire(key, timeout);
    }
    
    public void setSet(String key, Set<String> value) {
        for(String str:value){
            setOperations.add(key, str);    
            }
    }
    
    public Set<String> getSet(String key) {
        return setOperations.members(key);
    }

    public void setMap(String key, Map<String, Object> value, long timeout) {
        hashOperations.putAll(key, value);
        expire(key, timeout);
    }

    public void setMap(String key, Map<String, Object> value) {
        hashOperations.putAll(key, value);
    }

    public Map<String, Object> getMap(String key) {
        return hashOperations.entries(key);
    }

    /**
     * 得到缓存对象
     * 
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getCacheObject(String key) {
        if (template.hasKey(key)) {
            if (DataType.STRING.equals(template.type(key))) {
                return (T) valueOperations.get(key);
            } else if (DataType.LIST.equals(template.type(key))) {
                return (T) listOperations.range(key, 0, -1);
            } else if (DataType.SET.equals(template.type(key))) {
                return (T) setOperations.members(key);
            } else if (DataType.HASH.equals(template.type(key))) {
                return (T) hashOperations.entries(key);
            }
        }
        return null;
    }

    // 增加list获取值控制
    public ListOperations<String, String> getListOperations() {
        return listOperations;
    }

    /**
     * 移除对应key的缓存
     * 
     * @param key
     */
    public void removeCache(String key) {
        if (template.hasKey(key)) {
            template.delete(key);
        }
    }
    
    /**
     * 模式匹配删除
     * 
     * @paramkey
     */
    public void clearCache(String pattern) {
        Set<String> keys = template.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            template.delete(keys);
        }
    }

    /**
     * 获取当前缓存数量
     * */
    public long getRedisDBCount() {
        return this.getConnection().dbSize();
    }

    /**
     * 清空当前缓存
     * */
    public void clearCurrentRedisDB() {
        this.getConnection().flushDb();
    }
}
