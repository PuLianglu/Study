package com.pll.redisstudy.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: 测试controller
 * @author: puLiangLu
 * @create: 2022-09-01 00:54
 **/
@RestController
public class HelloController {

    @Resource
    private RedissonClient redissonClient;



    @GetMapping("/hello")
    public String hello() {
        RLock lock = redissonClient.getLock("anyLock");
        try {
            lock.lock();
            try {
                System.out.println("加锁成功，执行业务..." + Thread.currentThread().getId());
                TimeUnit.SECONDS.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            //lock.unlock();
            System.out.println("释放锁..." + Thread.currentThread().getId());
        }
        return "hello";
    }

}
