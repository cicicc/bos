package com.afeng.bos.web.action;

import com.afeng.bos.domain.Subarea;
import com.afeng.bos.service.ISubareaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
public class SubareaAction  extends BaseAction<Subarea>{
    //注入service层对象
    @Resource
    private ISubareaService subareaService;


    /**
     * 将页面程度过来的封装到model中的对象进行保存
     * @return 返回到列表页面
     */
    public String add(){
        subareaService.add(model);
        return LIST;
    }
}
