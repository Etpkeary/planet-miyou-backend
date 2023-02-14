package com.linglan.miyou.usercenter.service;

import com.linglan.miyou.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("LinglanString", "lanling");
        valueOperations.set("LinglanInt", 1);
        valueOperations.set("LinglanDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("Linglan");
        valueOperations.set("LinglanUser", user);
        // 查
        Object Linglan = valueOperations.get("LinglanString");
        Assertions.assertTrue("lanling".equals((String) Linglan));
        Linglan = valueOperations.get("LinglanInt");
        Assertions.assertTrue(1 == (Integer) Linglan);
        Linglan = valueOperations.get("LinglanDouble");
        Assertions.assertTrue(2.0 == (Double) Linglan);
        System.out.println(valueOperations.get("LinglanUser"));
        valueOperations.set("LinglanString", "lanling");
        redisTemplate.delete("LinglanString");
    }
}
