package com.asia.service;

import com.asia.data.UserXavier;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Xavier.liu
 * @date 2020/6/3 15:43
 */
public interface UserService {

    UserXavier getUser(Long id);

    void setUser(UserXavier user);

    @Async
    void remoteProcedureCall();
}
