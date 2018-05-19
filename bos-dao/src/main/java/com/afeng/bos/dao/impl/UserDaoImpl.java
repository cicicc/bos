package com.afeng.bos.dao.impl;

import com.afeng.bos.dao.IUserDao;
import com.afeng.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
    /**
     * 通过页面上用户输入的用户名和密码到数据库中进行查询 返回结果
     * @param username 用户输入的用户名
     * @param password 用户输入的密码
     * @return 若成功在数据库中查询到就返回user对象 否则返回null
     */
    @Override
    public User findUser(String username, String password) {
        String hql = "from User where username=? and password=?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
        User user = null;
        if (list.size() > 0 && list != null) {
           user = list.get(0);
        }
        return user;
    }

    /**
     * 对数据库中的此用户进行更新操作 修改密码
     * @param loginUser 当前的登录用户
     * 此处的代码可以进行语句的优化 但是暂时没有进行语句的优化
     */
    @Override
    public void modifyPassword(User loginUser) {
        update(loginUser);
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
