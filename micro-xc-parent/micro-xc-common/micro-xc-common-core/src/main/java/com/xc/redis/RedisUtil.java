package com.xc.redis;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
 
	/**
	 * 存放string类型
	 * 
	 * @param key
	 *            key
	 * @param data
	 *            数据
	 * @param timeout
	 *            超时间
	 */
	public  void setString(String key, String data, Long timeout) {

		stringRedisTemplate.opsForValue().set(key, data);
		if (timeout != null) {
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	public StringRedisTemplate getStringRedisTemplate(){
		return stringRedisTemplate;
	}

	public Boolean setNx(String key, String data, Long timeout) {

		Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, data);
		if (timeout != null) {
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
		return aBoolean;
	}
	/**
	 * 存放string类型
	 * 
	 * @param key
	 *            key
	 * @param data
	 *            数据
	 */
	public void setString(String key, String data) {
		setString(key, data, null);
	}

	public void setList(String key, List<String> tokens) {
		stringRedisTemplate.opsForList().leftPushAll(key,tokens);
	}
	/**
	 * 根据key查询string类型
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String value = stringRedisTemplate.opsForValue().get(key);
		return value;
	}
 
	/**
	 * 根据对应的key删除key
	 * 
	 * @param key
	 */
	public Boolean delKey(String key) {
		return stringRedisTemplate.delete(key);
	}


	/**
	 * 开启redis的事务
	 */
	public void begin(){
		//开启redis的事务权限
		stringRedisTemplate.setEnableTransactionSupport(true);
		//开启事务
		stringRedisTemplate.multi();
	}


	/**
	 * 提交事务
	 */
	public void exec(){
		//提交事务
		stringRedisTemplate.exec();
	}

	/**
	 * 事务回滚
	 */
	public void discard(){
		stringRedisTemplate.discard();
	}


}