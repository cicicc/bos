package com.afeng.bos.dao.impl;

import com.afeng.bos.dao.IBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    private Class<T> entityClass;//表示操作的实体类类型
    /**
     * 注入sessionFactory
     * 调用的父类方法源码 首先判断hibernateTemplate是否为空或者当前会话模板的sessionFactory和传递进来的sessionFactory不同
     * 就新建一个hibernateTemplate对象 赋值给hibernateTemplate变量
     * public final void setSessionFactory(SessionFactory sessionFactory) {
     * 		if (this.hibernateTemplate == null || sessionFactory != this.hibernateTemplate.getSessionFactory()) {
     * 			this.hibernateTemplate = createHibernateTemplate(sessionFactory);
     *                }
     *          }
     */
    @Resource//注入spring工厂中的会话工厂对象
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    public BaseDaoImpl(){
        //获得父类中的T所代表的类型
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得父类上声明的类型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        //将类型数组的第一个赋值给entityClass
        entityClass = (Class) actualTypeArguments[0];
    }
    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {

        return this.getHibernateTemplate().get(entityClass,id);
    }

    /**
     * 获取简单类名 并返回查询到的数据库中所有的数据结果
     * @return 数据库中所有对应的数据集合
     */
    @Override
    public List<T> findAll() {
        String hql = "from " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    /**
     * 保存或者修改用户传递过来的信息
     * @param entity 需要操作的数据实体
     */
    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }
}
