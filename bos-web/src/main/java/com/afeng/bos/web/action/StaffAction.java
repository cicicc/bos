package com.afeng.bos.web.action;

import com.afeng.bos.domain.Staff;
import com.afeng.bos.service.IStaffService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Resource
    private IStaffService staffService;

    /**
     * 添加派送员
     */
    public String addStaff(){
      staffService.addStaff(model);
        return null;
    }

}
