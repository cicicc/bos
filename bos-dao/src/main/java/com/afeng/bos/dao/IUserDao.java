package com.afeng.bos.dao;

import com.afeng.bos.domain.User;

public interface IUserDao extends IBaseDao<User>{

    User findUser(String username, String password);

    void modifyPassword(User loginUser);
}
