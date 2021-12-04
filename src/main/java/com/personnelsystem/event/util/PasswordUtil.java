package com.personnelsystem.event.util;

import com.personnelsystem.event.entity.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {

    /**
     * 密码校验
     * @param user 用户
     * @param password 密码
     */
    public static User validate(User user, String password) {
        if (!matches(user, password)) {
            return null;
        }
        return user;
    }

    /**
     * 密码校验判断
     * @param user 用户
     * @param password 密码
     * @return Boolean
     */
    public static boolean matches (User user, String password) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), password, user.getSalt()));
    }

    /**
     * 明文密码加密
     * @param userName 用户名
     * @param password 密码
     * @param salt 盐
     * @return string
     */
    public static String encryptPassword(String userName, String password, String salt) {
        return new Md5Hash(userName + password + salt).toHex();
    }

    /**
     * 生成随机盐
     * (用于创建账号)
     */
    public static String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(3).toHex();
    }
}
