package com.afeng.bos.dao.impl;

import com.afeng.bos.dao.ISubareaDao;
import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Subarea;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {
    /**
     * 查询数据库 得到数据库中的数据总数目 并返回
     * @return 数据库中数据总数目
     * @param pageBean 设置参数的pageBean对象
     */
    @Override
    public int getCount(PageBean pageBean) {
        pageBean.getDetachedCriteria().setProjection(Projections.count("id"));
        return 0;
    }
}
