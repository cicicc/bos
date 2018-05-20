package com.afeng.bos.dao.impl;

import com.afeng.bos.dao.IStaffDao;
import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Staff;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDaoImpl  extends BaseDaoImpl<Staff> implements IStaffDao{

    /**
     * 查询数据库并将数据封装到staffPageBean中 查询形式limit?,?
     * @param staffPageBean 传递过来的pageBean对象
     */
    @Override
    public void pageQuery(PageBean<Staff> staffPageBean) {
        int currentPage = staffPageBean.getCurrentPage();
        int pageSize = staffPageBean.getPageSize();
        //首先先查询数据库中数据的数量
        DetachedCriteria criteria = staffPageBean.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
        if (list != null && list.size() > 0) {
           staffPageBean.setTotal( list.get(0).intValue());
        }
        //将查询条件制空
        criteria.setProjection(null);
        //接着设置limit?,?中的两个参数 并执行查询
        int startIndex = (currentPage - 1) * pageSize;
        List<Staff> staffList = (List<Staff>) this.getHibernateTemplate().findByCriteria(criteria, startIndex, pageSize);
        staffPageBean.setRows(staffList);

    }

    /**
     *根据service层传递过来的数据 实现员工的逻辑删除
     * @param queryName 定义的查询语句
     * @param objects 要删除的id
     */
    @Override
    public void deleteStaffById(String queryName,  Object... objects) {
        Query namedQuery = this.getSessionFactory().getCurrentSession().getNamedQuery(queryName);
        int i = 0;
        for (Object object: objects) {
            namedQuery.setParameter(i++,object);
        }
            namedQuery.executeUpdate();
    }



}
