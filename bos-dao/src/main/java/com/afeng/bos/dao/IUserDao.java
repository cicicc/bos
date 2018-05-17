package com.afeng.bos.dao;

import com.afeng.bos.domain.User;

public interface IUserDao {

    User findUser(String username, String password);
}
