package com.example.shirodemo.service;

import com.example.shirodemo.bean.MUser;
import org.springframework.stereotype.Service;

/**
 * @author lizhangbo
 * @title: UserService
 * @projectName shirodemo
 * @description: TODO
 * @date 2020/6/1220:40
 */
@Service
public class UserServiceImpl implements UserService{
    public MUser queryUserById(String username){
        MUser mUser = new MUser();
        mUser.setId("1");
        mUser.setUsername("aaa");
        mUser.setPassword("123456");
        return mUser;
    }
}
