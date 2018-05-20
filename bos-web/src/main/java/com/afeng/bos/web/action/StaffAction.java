package com.afeng.bos.web.action;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Staff;
import com.afeng.bos.service.IStaffService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Resource
    private IStaffService staffService;
    private int page;//请求页面页码数
    private int rows;//请求页面数据数目
    private String ids;//页面请求删除的数据

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * 添加派送员
     */
    public String addStaff(){
      staffService.addStaff(model);
        return LIST;
    }

    /**
     * 根据页面传递过来的page和rows参数到数据库中进行查询
     */
    public String pageQuery() throws IOException {
        PageBean<Staff> staffPageBean = new PageBean<>();
        staffPageBean.setPageSize(rows);
        staffPageBean.setCurrentPage(page);
        //设置离线查询条件
        staffPageBean.setDetachedCriteria(DetachedCriteria.forClass(Staff.class));
        //调用service层查询数据
        staffService.pageQuery(staffPageBean);
        //将查询到的数据转换为json数据
        //使用jsonConfig指定哪些数据无需转换
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
        String json = JSONObject.fromObject(staffPageBean, jsonConfig).toString();
        //将数据写回页面
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    /**
     * 根据页面上传递过来的用户id删除用户
     * @return 回到列表页面
     */
    public String deleteStaff(){
        System.out.println(ids);
        //将id分割开来
        String[] idList = ids.split(",");
        staffService.deleteStaffById(idList);
        return LIST;
    }

    /**
     * 根据页面传来的用户信息进行修改
     * @return 回到收派员列表页面
     */
    public String editStaff(){
        String id = model.getId();
        //查询数据库中收派员信息
        Staff staff = staffService.findStaffById(id);
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStation(model.getStation());
        staff.setStandard(model.getStandard());
        staffService.updateStaff(staff);
        return LIST;
    }

}
