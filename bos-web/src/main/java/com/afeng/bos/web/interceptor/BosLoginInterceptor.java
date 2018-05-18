package com.afeng.bos.web.interceptor;

import com.afeng.bos.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class BosLoginInterceptor  extends MethodFilterInterceptor {
    /**
     * methodFilterInterceptor 实现了空的init和destroy方法
     *在这里我们只需要实现拦截器方法就可以了
     */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //从session中获取user对象 如果没有获取到的话 那么就跳转到登录页面
        User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "login";
        }
        //如果不为空 那么就放心
        return invocation.invoke();
    }
}
