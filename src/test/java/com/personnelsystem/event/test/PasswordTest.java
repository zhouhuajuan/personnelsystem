package com.personnelsystem.event.test;

import com.personnelsystem.event.util.PasswordUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: personnelsystem1
 * @description: 密码测试类
 * @author: 周华娟
 * @create: 2021-11-24 15:59
 **/
public class PasswordTest {

    @Test
    public void test(){
        String s = PasswordUtil.randomSalt();
        System.out.println(s);
        String password = "130106zhj";
        String admin = PasswordUtil.encryptPassword("admin", password, "a1e351");
        System.out.println(admin);
        //d20f44e67bbfdf6df32faf98957adedb
        //df1f155909dea364d6683d2e20e3fac0
    }
}
