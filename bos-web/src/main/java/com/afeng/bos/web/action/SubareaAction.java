package com.afeng.bos.web.action;

import com.afeng.bos.domain.Subarea;
import com.afeng.bos.service.ISubareaService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction  extends BaseAction<Subarea>{
    //注入service层对象
    @Resource
    private ISubareaService subareaService;
    //获取页面传递过来的page和rows参数
    //page: 1 所要获取的页面页码
    //rows: 30 每页显示多少数据
    private String page;
    private String rows;

    public void setPage(String page) {
        this.page = page;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    /**
     * 将页面程度过来的封装到model中的对象进行保存
     * @return 返回到列表页面
     */
    public String add(){
        subareaService.add(model);
        return LIST;
    }

    /**
     * 查询数据库中所有的区域
     * @return 异步查询所有 所以无需返回到其他页面
     */
    public String findAll() {
        pageBean.setTotal(subareaService.gedCount());
        pageBean.setDetachedCriteria(DetachedCriteria.forClass(Subarea.class));
        pageBean.setCurrentPage(Integer.valueOf(page));
        pageBean.setPageSize(Integer.valueOf(rows));
        List<Subarea> subareaList = subareaService.pageQuery(pageBean);
        return NONE;
    }
}
