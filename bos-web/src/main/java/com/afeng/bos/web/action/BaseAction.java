package com.afeng.bos.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;
    public static final String HOME = "home";
    public static final String LIST = "list";

    @Override
    public T getModel() {
        return model;
    }
    //在构造方法中动态获取实体类的类型 动态创建实体类
    public BaseAction(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> actualTypeArgument = (Class) actualTypeArguments[0];
        try {
            //使用反射动态创建模型对象
            model = actualTypeArgument.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
