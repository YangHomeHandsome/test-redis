package com.itmayiedu.Util;

/**
 * Created by YJJ on 2019/9/28.
 */
public class FreshRedisLockTimeThread extends Thread {
    private RedisUtil redisUtil;
    private String key;
    private String uuid;

    public FreshRedisLockTimeThread(RedisUtil r, String key,String uuid) {
        redisUtil = r;
        this.key = key;
        this.uuid=uuid;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object o = null;
                if ((o = redisUtil.get(key))!=null&&uuid.equals(o.toString())) {
                    if (redisUtil.getExpire(key) < 3) {
                        redisUtil.expire(key, 10);
                    }
                }else{
                    return;
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
