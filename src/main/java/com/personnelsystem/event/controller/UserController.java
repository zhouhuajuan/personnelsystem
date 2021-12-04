package com.personnelsystem.event.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.util.StringUtils;
import com.personnelsystem.event.entity.User;
import com.personnelsystem.event.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: personnelsystem1
 * @description: 用户控制层
 * @author: 周华娟
 * @create: 2021-11-24 13:27
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){
        User login = userService.login(username, password);
        if (!ObjectUtil.isEmpty(login)){
            session.setAttribute("loginUser",login);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","用户名或者密码错误！");
            return "index";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
