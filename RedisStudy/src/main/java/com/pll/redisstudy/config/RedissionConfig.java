package com.pll.redisstudy.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: demo
 * @description: Redission配置
 * @author: puLiangLu
 * @create: 2022-09-01 00:30
 **/
@Configuration
public class RedissionConfig {

    /**
     * 所有对Redisson的使用都是通过RedissonClient对象
     * @return
     */
    @Bean(destroyMethod = "shutdown")   // 服务停止以后，会调用shutdown进行销毁
    RedissonClient redission() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }

}
