package com.itmayiedu.Controller;

import com.itmayiedu.Util.FreshRedisLockTimeThread;
import com.itmayiedu.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by YJJ on 2019/9/28.
 */
@RestController
public class ProductController {
    @Autowired
    RedisUtil redisUtil;


    //记录本机卖出记录
    static int localSelaCount = 0;

    @PostMapping("addStoreByFlashProduct")
    public Object addStoreByFlashProduct(Integer productId, Integer storeNumber) {
        /*if (!redisUtil.hasKey(productId.toString())){
            return "hasn't this product";
        }*/
        if (storeNumber < 0) {
            return "illegal numbers for store";
        }
        //测试用  直接set
        redisUtil.set("product:" + productId.toString(), storeNumber);
        localSelaCount = storeNumber / 2;
        return redisUtil.get("product:" + productId.toString());
    }


    //通过倒计时帮助没获取锁的线程进行等待
    static CountDownLatch countDownLatch = null;

    @PostMapping("flashBuy")
    public Object flashBuy(HttpServletRequest request, Integer productId) {
        String sessionId = request.getRequestedSessionId();
        //System.out.print("sessionId= "+sessionId+" ");
        if (!redisUtil.hasKey("product:" + productId.toString())) {
            return "hasn't this product";
        }
        String threadId = UUID.randomUUID().toString().replace("-", "");
        threadId = request.getLocalPort() + "  " + threadId;
        String key = "lockProduct:" + productId;//准备key和value
        synchronized (redisUtil) {
            try {
                while (true) {//重试5次获取锁，或者返回活动火爆
                    if (redisUtil.setnx(key, threadId, 10)) {//没有获取到锁
                        //获取到了  另起一个线程去刷新过期时间;
                        countDownLatch = new CountDownLatch(1);
                        new FreshRedisLockTimeThread(redisUtil, key, threadId).start();
                        break;
                    } else {
                        //没有获得锁的去等待
                        if (countDownLatch != null) {
                            countDownLatch.await();
                        }
                    }
                }
                //获取到锁了执行
            /*Object myStore=redisUtil.get("product:"+productId.toString()+request.getLocalPort());
            if (myStore==null){
                redisUtil.set("product:"+productId.toString()+request.getLocalPort(),0);
            }else if(Integer.parseInt(myStore.toString())<1){

            }*/
                long store = Integer.parseInt(redisUtil.get("product:" + productId.toString()).toString());
                if (store > 0) {
                    redisUtil.decr("product:" + productId.toString(), 1L);
                    System.out.println(threadId + "号客户成功购买 " + redisUtil.get("product:" + productId.toString()) + new Date(System.currentTimeMillis()));
                } else {
                    System.out.println("库存不足" + new Date(System.currentTimeMillis()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                if (threadId.equals(redisUtil.get(key))) {
                    redisUtil.del(key);
                }
            }
        }
        return null;
    }


    @RequestMapping("flashBuy2")
    public Object flashBuy2(HttpServletRequest request, Integer productId) {
        String threadId = UUID.randomUUID().toString().replace("-", "");
        threadId = request.getLocalPort() + "  " + threadId;
        synchronized (redisUtil) {
            long store = 0;
            Object myStore = redisUtil.get("product:" + productId.toString() + request.getLocalPort());
            if (myStore == null) {
                redisUtil.set("product:" + productId.toString() + request.getLocalPort(), 0);
                if (!getStore("product:" + productId.toString() + request.getLocalPort(), "lockProduct:" + productId, threadId)) {
                    System.out.println("库存不足" + new Date(System.currentTimeMillis()));
                    return "库存不足";
                }
            } else if (Integer.parseInt(myStore.toString()) < 1) {
                if (!getStore("product:" + productId.toString() + request.getLocalPort(), "lockProduct:" + productId, threadId)) {
                    System.out.println("库存不足" + new Date(System.currentTimeMillis()));
                    return "库存不足";
                }
            }
            redisUtil.decr("product:" + productId.toString() + request.getLocalPort(), 1);
            System.out.println(threadId + "号客户成功购买 " + (localSelaCount--) + "  " + new Date(System.currentTimeMillis()));
            return "seccues";
        }
    }

    //从redis里面取100个库存到本机
    public boolean getStore(String localKey, String key, String threadId) {
        try {
            while (!redisUtil.setnx(key, threadId, 10)) ;
            long store = Integer.parseInt((redisUtil.get("product:" + 100).toString()));
            if (store > 200) {
                redisUtil.decr("product:" + 100, 100);
                redisUtil.incr(localKey, 100);
            } else if (store > 1) {
                redisUtil.decr("product:" + 100, store / 2);
                redisUtil.incr(localKey, store / 2);
            } else if (store == 1) {
                redisUtil.decr("product:" + 100, 1);
                redisUtil.incr(localKey, 1);

            } else {
                return false;
            }
            return true;
        } finally {
            if (threadId.equals(redisUtil.get(key))) {
                redisUtil.del(key);
            }
        }

    }

    public static void main(String[] args) {

    }
}
