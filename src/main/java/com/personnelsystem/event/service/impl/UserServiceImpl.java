package com.personnelsystem.event.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.personnelsystem.event.entity.User;
import com.personnelsystem.event.mapper.UserMapper;
import com.personnelsystem.event.service.UserService;
import com.personnelsystem.event.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: personnelsystem1
 * @description:
 * @author: 周华娟
 * @create: 2021-11-23 09:04
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if(ObjectUtil.isNull(user)){
            //throw new UserNotExistException();
            //用户不存在，也就是账号错误
            return null;
        }
        User validate = PasswordUtil.validate(user, password);
        if (ObjectUtil.isEmpty(validate)){
            //密码不一致
            return null;
        }
        return user;
    }
}
