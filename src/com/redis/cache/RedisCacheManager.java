package com.redis.cache;

import java.util.*;
import java.util.logging.Logger;

public class RedisCacheManager {

    private static RedisCacheManager instance;
    private static Object monitor = new Object();
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());
    private Logger LOGGER = Logger.getLogger("RedisCacheManager");
    
    private RedisCacheManager() {
    	LOGGER.info("Caching started");
    }

    public void put(String cacheKey, Object value) {
        cache.put(cacheKey, value);
        LOGGER.info("Added to the cache :: "+cacheKey);
    }

    public Object get(String cacheKey) {
    	LOGGER.info("Cache being fetched :: "+cacheKey);
        return cache.get(cacheKey);
    }

    public void remove(String cacheKey) {
        cache.put(cacheKey, null);
        LOGGER.info("Removed from the cache :: "+cacheKey);
    }

    public void removeAll() {
        cache.clear();
        LOGGER.info("All caches removed");
    }

     public int size() {
    	 return cache.size();
     }
    public static RedisCacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new RedisCacheManager();
                }
            }
        }
        return instance;
    }
}