package org.redis;

import org.database.MysqlTestApplication;
import org.database.redis.RedisComp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yxl17
 * @Package : org.redis
 * @Create on : 2023/12/22 00:04
 **/

@SpringBootTest(classes = MysqlTestApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestRedis {

    @Autowired
    private RedisComp redisComp;


    //增加权限
    @Test
    public void testRedisGet() {
        for (int i = 0; i < 100; i++) {
            redisComp.set("key" + i, "value" + i);
        }
        System.out.println("权限创建成功！");
    }

    @Test
    public void testRedisSet() {
        for (int i = 0; i < 100; i++) {
            System.out.println(redisComp.get("key" + i));
        }
    }

    @Test
    public void testRedisFlushAll() {
        for (int i = 0; i < 100; i++) {
            redisComp.del("key" + i);
        }
    }
}
