package com.afeng.bos.service.impl;

import com.afeng.bos.dao.IUserDao;
import com.afeng.bos.domain.User;
import com.afeng.bos.service.IUserService;
import com.afeng.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    @Transactional(readOnly = true)
    public User findUser(User model) {
        String username = model.getUsername();
        String password = MD5Utils.md5(model.getPassword());
        return userDao.findUser(username,password);
    }
}
