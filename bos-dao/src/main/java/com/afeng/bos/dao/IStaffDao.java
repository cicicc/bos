package com.afeng.bos.dao;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Staff;

public interface IStaffDao extends IBaseDao<Staff>{
    void save(Staff model);

    void pageQuery(PageBean<Staff> staffPageBean);


    void deleteStaffById(String s, Object... objects);
}
