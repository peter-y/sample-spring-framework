package com.geolisa.support.redis;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil implements Cache {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private String name;

    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        if (logger.isDebugEnabled()) {
            logger.debug("getName : {}", this.name);
        }
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        if (logger.isDebugEnabled()) {
            logger.debug("getNativeCache ...");
        }
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (logger.isDebugEnabled()) {
            logger.debug("get Key : {}", key);
        }
        final String keyf = key.toString();
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                throws DataAccessException {
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return toObject(value);
            }
        });
        return (object != null ? new SimpleValueWrapper(object) : null);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        if (logger.isDebugEnabled()) {
            logger.debug("get Key : {} type : {}", key, type);
        }
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        if (logger.isDebugEnabled()) {
            logger.debug("get Key : {} valueLoader : {}", key, valueLoader);
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if (logger.isDebugEnabled()) {
            logger.debug("put Key : {} value : {}", key, value);
        }
        final String keyf = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                throws DataAccessException {
                byte[] keyb = keyf.getBytes();
                byte[] valueb = toByteArray(valuef);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (logger.isDebugEnabled()) {
            logger.debug("putIfAbsent Key : {} value : {}", key, value);
        }
        return null;
    }

    @Override
    public void evict(Object key) {
        if (logger.isDebugEnabled()) {
            logger.debug("evict Key : {}", key);
        }
        System.out.println("del key");
        final String keyf = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
        });
    }

    @Override
    public void clear() {
        if (logger.isDebugEnabled()) {
            logger.debug("clear");
        }
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}
