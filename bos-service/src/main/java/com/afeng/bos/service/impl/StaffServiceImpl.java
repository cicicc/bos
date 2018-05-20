package com.afeng.bos.service.impl;

import com.afeng.bos.dao.IStaffDao;
import com.afeng.bos.domain.PageBean;
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
//        System.out.println(model);
        staffDao.save(model);
    }

    /**
     *调用dao层查询指定位置的数据 事务设置为只读
     * @param staffPageBean action类传递过来的参数 也将我们查询到的数据封装入其中
     */
    @Transactional(readOnly = true)
    @Override
    public void pageQuery(PageBean<Staff> staffPageBean) {
        staffDao.pageQuery(staffPageBean);
    }

    /**
     * 调用dao层删除指定的数据
     * @param idList 要删除的id集合
     */
    @Override
    public void deleteStaffById(String[] idList) {
        for (int i = 0; i <idList.length ; i++) {
        staffDao.deleteStaffById("staff.delete",idList[i]);
        }
    }

    /**
     * 通过收派员id查询指定的收派员信息 并返回
     * @param id 收派员id
     * @return 收派员bean对象
     */
    @Transactional(readOnly = true)
    @Override
    public Staff findStaffById(String id) {
        return staffDao.findById(id);
    }

    /**
     * 更新数据库中某一个具体的收派员的信息
     * @param staff 保存更新后收派员信息对对象
     */
    @Override
    public void updateStaff(Staff staff) {
        staffDao.update(staff);
    }
}
