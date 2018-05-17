package com.afeng.bos.dao.impl;

import com.afeng.bos.dao.IUserDao;
import com.afeng.bos.domain.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    @Override
    public User findUser(String username, String password) {
        String hql = "from User where username=? and password=?";
        User user = (User) this.getHibernateTemplate().find(hql, username,password).get(0);
        System.out.println(user);
        return user;
    }

    /**
     * 单元测试
     * 这个代码根本无法跑起来 会报nullPointerException
     * 很简单的原因 因为在这个过程中 并没有注入userDao 所以hibernateTemplate就是空
     */
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setId("111");
        user.setUsername("ccc");
        user.setPassword("ccc");
        User user1 = userDao.findUser(user.getUsername(), user.getPassword());
    }

}
