package com.afeng.bos.utils;

import com.afeng.bos.domain.User;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;


public class SessionUtils {

    /**
     * 获取session域对象
     */
    public static HttpSession getSession(){

      return  ServletActionContext.getRequest().getSession();
    }
    /**
     * 获取当前登录的用户
     */
    public static User getLoginUser(){

        return (User)getSession().getAttribute("loginUser");
    }
}
