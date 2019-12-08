package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

@SpringBootApplication
/*@ComponentScan(value = {"com.itmayiedu.mapper"})*///要用MapperScan扫描
@MapperScan(basePackages = "com.itmayiedu.mapper")
public class SpringbootDemo1Application {

	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	StringRedisTemplate rs;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SpringbootDemo1Application.class, args);
		HashSet s=new HashSet();
		TreeSet ss=new TreeSet();
		ss.add(1);
		SynchronousQueue sss=new SynchronousQueue();
		sss.put(1);
	}


}
