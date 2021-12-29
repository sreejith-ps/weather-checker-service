package com.etslt.oss.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration tenMinCache = new CacheConfiguration();
		tenMinCache.setName("ten-minute-cache"); //read these values from properties
		tenMinCache.setMemoryStoreEvictionPolicy("LRU");
		tenMinCache.setMaxBytesLocalHeap(10000l);
//		tenMinCache.setMaxEntriesInCache(1000);
		tenMinCache.setTimeToLiveSeconds(600);
		
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(tenMinCache);
		
		return net.sf.ehcache.CacheManager.newInstance(config);
	}
	
	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
}
