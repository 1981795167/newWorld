package com.asia.service.impl;

import com.asia.data.UserXavier;
import com.asia.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Xavier.liu
 * @date 2020/6/3 15:44
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Redisson redisson;



    @Override
    public UserXavier getUser(Long id) {
        Object object = redisTemplate.opsForValue().get("redisKeyXavier:" + id);
        if (Objects.isNull(object)) {
            log.info("not hit redis cache------------------");
            return mockUser(id);
        } else {
            return (UserXavier) object;
        }
    }

    @Override
    public void setUser(UserXavier user) {
        redisTemplate.opsForValue().set("redisKeyXavier:" + user.getId(),user);
        redisTemplate.expire("redisKeyXavier:" + user.getId(),60000, TimeUnit.MILLISECONDS);
        redisTemplate.opsForValue().setIfAbsent("redisKeyXavier", UUID.randomUUID().toString(),1000,TimeUnit.MILLISECONDS);

        RLock rLock = redisson.getLock("rediskey");
        try {
            rLock.tryLock(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (rLock.isHeldByCurrentThread()){
                rLock.unlock();
            }
        }
    }

    @Override
    public void remoteProcedureCall() {
        log.info("rpc start ...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("rpc return result ...");
    }

    private UserXavier mockUser(Long id){
        return UserXavier
                .builder()
                .id(id)
                .name("mockUsrName")
                .age(18)
                .sex(1)
                .build();
    }
}
