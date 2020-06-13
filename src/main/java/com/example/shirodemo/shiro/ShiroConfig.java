package com.example.shirodemo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lizhangbo
 * @title: ShiroConfig
 * @projectName shirodemo
 * @description: Shiro配置类
 * @date 2020/6/1220:47
 */
@Configuration
public class ShiroConfig {
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器DefaultWebSecurityManager
        bean.setSecurityManager(securityManager);
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *  常用的过滤器：
         *      anon： 无需认证（登录）可以访问
         *      authc： 必须认证才可以访问
         *      user： 如果使用rememberMe的功能可以直接访问
         *      perms： 该资源必须得到角色权限才可以访问
         *      role： 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //        filterMap.put("/user/add", "authc");
        //        filterMap.put("/user/update", "authc");
        //对于相似的资源，需要将anon的设置放在authc前面，anon才会生效，因为Shiro是从上往下匹配URL的，匹配成功便不再匹配了
        filterMap.put("/user/*", "authc");
        filterMap.put("/doLogin", "anon");
        filterMap.put("/**", "authc");
        //修改调整的登录页面，否则就是默认的login
        bean.setLoginUrl("/index");

        //未授权
        bean.setUnauthorizedUrl("403");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

}
