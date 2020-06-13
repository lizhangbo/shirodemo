package com.example.shirodemo.service;

import com.example.shirodemo.bean.MUser;

/**
 * @author lizhangbo
 * @title: UserService
 * @projectName shirodemo
 * @description: TODO
 * @date 2020/6/1221:35
 */
public interface UserService {
    public MUser queryUserById(String username);
}
