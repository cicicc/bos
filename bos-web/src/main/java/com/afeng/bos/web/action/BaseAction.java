package com.afeng.bos.web.action;

import com.afeng.bos.domain.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    //在子类中需要使用到这些数据 所以在这里将这些数据设置为了protected
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
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * baseAction类抽取子类的代码
     * 将查询到的结果转换为json对象 返回到页面中
     * @param object 需要进行转换的对象
     * @param excludes 无需转换成json的对象中的数据
     * @throws IOException 抛出IO异常
     */
    public void switchObjectToJson(Object object, String[] excludes) throws IOException {
        //将所得到的数据使用json进行对应的转换
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        //将pageBean对象中的数据转换为json对象 并返回给调用者
        String json =JSONObject.fromObject(object, jsonConfig).toString();
        //将数据放入response域中 写回页面
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(json);
    }


     /**
     * baseAction类抽取子类的代码
     * 将查询到的结果转换为json对象 返回到页面中
     * @param list 需要进行转换的数组对象
     * @param excludes 无需转换成json的对象中的数据
     * @throws IOException 抛出IO异常
     */
     public void switchObjectToJson(List list, String[] excludes) throws IOException {
        //将所得到的数据使用json进行对应的转换
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        //将pageBean对象中的数据转换为json对象 并返回给调用者
        String json =JSONArray.fromObject(list, jsonConfig).toString();
        //将数据放入response域中 写回页面
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(json);
    }

}
