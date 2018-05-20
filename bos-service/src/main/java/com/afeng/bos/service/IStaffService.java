package com.afeng.bos.service;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Staff;

public interface IStaffService {
    void addStaff(Staff model);

    void pageQuery(PageBean<Staff> staffPageBean);

    void deleteStaffById(String[] idList);

    Staff findStaffById(String id);

    void updateStaff(Staff staff);
}
