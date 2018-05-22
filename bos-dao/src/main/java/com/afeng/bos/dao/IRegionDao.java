package com.afeng.bos.dao;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region>{
    void pageQuery(PageBean pageBean);
}
