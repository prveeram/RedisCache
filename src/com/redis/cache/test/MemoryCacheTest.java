package com.redis.cache.test;

import com.redis.cache.RedisCacheManager;

public class MemoryCacheTest {
 
    public static void main(String[] args) throws InterruptedException {
 
        MemoryCacheTest memoryCache = new MemoryCacheTest();
 
        System.out.println("\n\n==========Test1: testAddRemoveObjects ==========");
        memoryCache.testAddRemoveObjects();
        System.out.println("\n\n==========Test2: testExpiredCacheObjects ==========");
        memoryCache.testExpiredCacheObjects();
        System.out.println("\n\n==========Test3: testObjectsCleanupTime ==========");
        memoryCache.testRemoveAllObjectsTime();
    }
 
    private void testAddRemoveObjects() {
    	RedisCacheManager cache = RedisCacheManager.getInstance();
        
        cache.put("eBay", "eBay");
        cache.put("Paypal", "Paypal");
        cache.put("Google", "Google");
        cache.put("Microsoft", "Microsoft");
        cache.put("IBM", "IBM");
        cache.put("Facebook", "Facebook");
 
        System.out.println("6 Cache Object Added.. cache.size(): " + cache.size());
        cache.remove("IBM");
        System.out.println("One object removed.. cache.size(): " + cache.size());
 
        cache.put("Twitter", "Twitter");
        cache.put("SAP", "SAP");
        System.out.println("Two objects Added but reached maxItems.. cache.size(): " + cache.size());
 
    }
 
    private void testExpiredCacheObjects() throws InterruptedException {
 
    	RedisCacheManager cache = RedisCacheManager.getInstance();
        cache.put("eBay", "eBay");
        cache.put("Paypal", "Paypal");
        // Adding 3 seconds sleep.. Both above objects will be removed from
        Thread.sleep(3000);
 
        System.out.println("Two objects are added but reached timeToLive. cache.size(): " + cache.size());
 
    }
 
    private void testRemoveAllObjectsTime() throws InterruptedException {
        int size = 500000;
 
        RedisCacheManager cache = RedisCacheManager.getInstance();
        for (int i = 0; i < size; i++) {
            String value = Integer.toString(i);
            cache.put(value, value);
        }
 
        Thread.sleep(200);
 
        long start = System.currentTimeMillis();
        cache.removeAll();
        double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
 
        System.out.println("Cleanup times for " + size + " objects are " + finish + " s");
 
    }
}