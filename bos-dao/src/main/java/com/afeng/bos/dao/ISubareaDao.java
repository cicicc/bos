package com.afeng.bos.dao;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea> {
    int getCount(PageBean pageBean);
}
