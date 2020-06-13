package com.example.shirodemo.controller;

import com.example.shirodemo.bean.Cif;
import com.example.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhangbo
 * @title: UserController
 * @projectName shirodemo
 * @description: TODO
 * @date 2020/6/1220:40
 */
@Controller
public class UserController {
    @RequestMapping("/user/add")
    public String user_add(){
        System.out.println("user_add......");
        return "/user/addUser";
    }
    @RequestMapping("/user/doAdd")
    public String user_addConfirm(Cif cif){
        System.out.println(cif);
        return "redirect:/user/user_home.html";
    }

    @RequestMapping("/index")
    public String index(){
        System.out.println("coming......");
        return "login";
    }
    @RequestMapping("/doLogin")
    public String login(String username, String password) {
        System.out.println("doLogin");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            return "redirect:home.html";
//            return "home";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "/login";
        }
    }
    /**
     * 退出
     * @return
     */
    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){

        //subject的实现类DelegatingSubject的logout方法，将本subject对象的session清空了
        //即使session托管给了redis ，redis有很多个浏览器的session
        //只要调用退出方法，此subject的、此浏览器的session就没了
        try {
            //退出
            SecurityUtils.getSubject().logout();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "login";

    }

    @RequestMapping(value="403")
    public String unAuth() {
        return "403";
    }
}
