package com.afeng.bos.dao;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region>{
    void pageQuery(PageBean pageBean);

    List<Region> findByQ(String q);

}
