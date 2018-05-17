package com.afeng.bos.web.action;

import com.afeng.bos.domain.User;
import com.afeng.bos.service.IUserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.Cookie;

@Controller
@Scope("prototype")
public class UserAction  extends BaseAction<User>{
    //注入登录页面的验证码
    private String checkcode;

    @Autowired
    private IUserService userService;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String login(){
        //获取存放在session域中的key
        String realCheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //首先将页面用户输入的验证码和session域中的验证码进行比较 如果相同的话才进行下一步
        if (StringUtils.isNotBlank(checkcode)&&realCheckCode.equals(checkcode)) {
            User user = userService.findUser(model);
            if (user == null) {
                this.addActionError("用户名或者密码错误");
            }else{
                Cookie loginUser = new Cookie("loginUser", user.getUsername() + "@" + user.getPassword());
                ServletActionContext.getResponse().addCookie(loginUser);
                return HOME;
            }
        }else{
            this.addActionError("您的验证码输入有误,请重新输入");
        }
            return LOGIN;
    }

}
