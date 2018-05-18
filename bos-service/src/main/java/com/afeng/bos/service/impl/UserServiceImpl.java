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

    /**
     * 根据action传递过来的数据 将用户密码进行加密 并将用户名和密码传递到dao层进行数据库的相关查询
     * 由于是查询 所以讲数据库得只读设为true
     * @param model action类传递过来的User对象
     * @return dao层查询到的结果 null或者一个user对象
     */
    @Override
    @Transactional(readOnly = true)
    public User findUser(User model) {
        String username = model.getUsername();
        String password = MD5Utils.md5(model.getPassword());
        return userDao.findUser(username,password);
    }
}
