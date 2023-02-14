package com.linglan.miyou.usercenter.service;

import com.linglan.miyou.model.domain.User;
import com.linglan.miyou.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author LingLan
 */
@SpringBootTest
public class InserUsersTest {

    @Resource
    private UserService userService;

    // CPU 密集型：分配的核心线程数 = CPU - 1
    // IO 密集型：分配的核心线程数可以大于 CPU 核数
    private final ExecutorService executorService = new ThreadPoolExecutor(60, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    /**
     * 批量插入数据
     */
    @Test
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100000;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("莫妮卡");
            user.setUserAccount("monika");
            user.setAvatarUrl("https://636f-codenav-8grj8px727565176-1256524210.tcb.qcloud.la/img/logo.png");
            user.setGender(1);
            user.setUserPassword("12345678");
            user.setPhone("12345678");
            user.setEmail("123@qq.com");
            user.setTags("[]");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("11111");
            userList.add(user);
        }
        userService.saveBatch(userList, 1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    /**
     * 并发 批量插入数据
     */
    @Test
    public void doConcureencyInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int j = 0;
        int batchSize = 1000;
        int numConcurrent = 20;
        List<CompletableFuture> futureList = new ArrayList<>();
        for (int i = 0; i < numConcurrent; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
                User user = new User();
                user.setUsername("莫妮卡");
                user.setUserAccount("monika");
                user.setAvatarUrl("https://636f-codenav-8grj8px727565176-1256524210.tcb.qcloud.la/img/logo.png");
                user.setGender(1);
                user.setUserPassword("12345678");
                user.setPhone("12345678");
                user.setEmail("123@qq.com");
                user.setTags("[]");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setPlanetCode("11111");
                userList.add(user);
                if (j % 10000 == 0) {
                    break;
                }
            }
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("ThreadName:" + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            }, executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}