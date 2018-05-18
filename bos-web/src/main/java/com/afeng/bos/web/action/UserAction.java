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

    /**
     * 登录模块的校验
     * 首先判断用户的验证模式是否输入正确 如果输入正确再进行下一步 判断用户名和密码输入是否正确
     * 否则直接返回登录页面并提示用户验证码输入错误
     * @return 验证码,用户名,密码三者如果有一个输入错误 都会返回到登录页面去并告知错误信息 全都输入正确进入首页
     */
    public String login(){
        //获取存放在session域中的key
        String realCheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //首先将页面用户输入的验证码和session域中的验证码进行比较 如果相同的话才进行下一步
        if (StringUtils.isNotBlank(checkcode)&&realCheckCode.equals(checkcode)) {
            User user = userService.findUser(model);
            if (user == null) {
                this.addActionError("用户名或者密码错误");
            }else{
//                Cookie loginUser = new Cookie("loginUser", user.getUsername() + "@" + user.getPassword());
                ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
                return HOME;
            }
        }else{
            this.addActionError("您的验证码输入有误,请重新输入");
        }
            return LOGIN;
    }

    /**
     * 登出模块 由于暂时并没有设计自动登录模块 所以在这里我们并没有把用户信息放入cookie中
     * 仅仅是放入了session中 用户退出登录的话就将用户session从服务器端清除
     * @return 返回到登录页面
     */
    public String logout(){
        //移除cookie和session(暂时只有session)
        ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
        return LOGIN;

    }

}
