package com.afeng.bos.service.impl;

import com.afeng.bos.dao.IStaffDao;
import com.afeng.bos.domain.Staff;
import com.afeng.bos.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffDao staffDao;


    /**
     * 保存用户传来的数据
     * @param model staff类 由页面传递来的数据注入
     */
    @Override
    public void addStaff(Staff model) {
        System.out.println(model);
        staffDao.save(model);
    }
}
