package com.afeng.bos.dao.impl;

import com.afeng.bos.dao.IRegionDao;
import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Region;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao  {

    /**
     * 查询数据库并将数据封装到 RegionPageBean中 查询形式limit?,?
     * @param  RegionPageBean 传递过来的pageBean对象
     */
    @Override
    public void pageQuery(PageBean RegionPageBean) {
        int currentPage = RegionPageBean.getCurrentPage();
        int pageSize = RegionPageBean.getPageSize();
        //首先先查询数据库中数据的数量
        DetachedCriteria criteria = RegionPageBean.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
        if (list != null && list.size() > 0) {
            RegionPageBean.setTotal( list.get(0).intValue());
        }
        //将查询条件制空
        criteria.setProjection(null);
        //接着设置limit?,?中的两个参数 并执行查询
        int startIndex = (currentPage - 1) * pageSize;
        List<Region> RegionList = (List<Region>) this.getHibernateTemplate().findByCriteria(criteria, startIndex, pageSize);
        RegionPageBean.setRows(RegionList);

    }

    @Override
    public List<Region> findByQ(String q) {
        String hql = "from Region r where r.shortcode like ? or r.citycode like ?";
        return (List<Region>) this.getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%");
    }



}
