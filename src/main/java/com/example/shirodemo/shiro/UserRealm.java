package com.example.shirodemo.shiro;

import com.example.shirodemo.bean.MUser;
import com.example.shirodemo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lizhangbo
 * @title: UserRealm
 * @projectName shirodemo
 * @description: 自定义Realm
 * @date 2020/6/1220:45
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        System.out.println("用户名："+username);
        MUser mUser = userService.queryUserById(username);
        if (mUser==null){
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo= new SimpleAuthenticationInfo(username, mUser.getPassword(), getName());
        return authenticationInfo;
    }
}
