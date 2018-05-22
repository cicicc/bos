package com.afeng.bos.web.action;

import com.afeng.bos.domain.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.hibernate.criterion.DetachedCriteria;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;
    protected static final String HOME = "home";
    protected static final String LIST = "list";
    //创建pageBean对象
    protected PageBean pageBean = new PageBean();
    protected DetachedCriteria detachedCriteria = null;


    @Override
    public T getModel() {
        return model;
    }
    //在构造方法中动态获取实体类的类型 动态创建实体类
    public BaseAction(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> actualTypeArgument = (Class) actualTypeArguments[0];
        //创建离线查询条件 并将数据封装到pageBean中去
        detachedCriteria = DetachedCriteria.forClass(actualTypeArgument);
        pageBean.setDetachedCriteria(detachedCriteria);

        try {
            //使用反射动态创建模型对象
            model = actualTypeArgument.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * baseAction类抽取子类的代码
     */
    protected String switchObjectToJson(String[] excludes){
        //将所得到的数据使用json进行对应的转换
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        //将pageBean对象中的数据转换为json对象 并返回给调用者
        return  JSONObject.fromObject(pageBean, jsonConfig).toString();
    }

}
