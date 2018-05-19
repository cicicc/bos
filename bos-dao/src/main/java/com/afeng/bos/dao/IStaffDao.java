package com.afeng.bos.dao;

import com.afeng.bos.domain.Staff;

public interface IStaffDao extends IBaseDao<Staff>{
    void save(Staff model);
}
