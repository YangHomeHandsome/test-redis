package com.itmayiedu.james;

import com.itmayiedu.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by YJJ on 2019/10/9.
 */
@RestController
public class Homework {



    public static void main(String[] args) {

        long val=30L;
        BigInteger result=new BigInteger("1");
        for(int i=2;i<=30;i++){
            result=result.multiply(new BigInteger(i+""));
            System.out.println(i+" ="+result.toString());
        }
        System.out.println(result.toString());
        System.out.println(25*28);
    }

    @Autowired
    RedisUtil redisUtil;
    @RequestMapping("redisHomework")
    public void myRedisHomeWord(){

        Object o=redisUtil.getTest("dsdsd");
        System.out.println(o);

        HashMap<String,Object> map=new HashMap<>();
        map.put("orderId",1);
        map.put("money",37.6);
        map.put("time","2018-01-01");
        redisUtil.hmset("order:1",map);
        map.put("orderId",2);
        map.put("money",38.6);
        map.put("time","2018-01-01");
        redisUtil.hmset("order:2",map);
        map.put("orderId",3);
        map.put("money",39.6);
        map.put("time","2018-01-01");
        redisUtil.hmset("order:3",map);
        List<Object> orders=new ArrayList<>();
        orders.add(redisUtil.hmget("order:1"));
        orders.add(redisUtil.hmget("order:2"));
        orders.add(redisUtil.hmget("order:3"));
        redisUtil.lSet("user:1:order",orders);
        map.put("orderId",4);
        map.put("money",40.6);
        map.put("time","2018-01-01");
        redisUtil.hmset("order:4",map);
        redisUtil.lSet("user:1:order",map);
        orders=redisUtil.lGet("user:1:order",0,-1);
        System.out.println(orders.toString());
    }
}
