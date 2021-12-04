package com.personnelsystem.event.service;

import com.personnelsystem.event.entity.User;

public interface UserService {

    User login(String username,String password);
}
