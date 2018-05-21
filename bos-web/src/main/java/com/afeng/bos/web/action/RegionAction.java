package com.afeng.bos.web.action;

import com.afeng.bos.domain.Region;
import com.afeng.bos.service.IRegionService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction  extends BaseAction<Region>{

    //接收页面传递过来的文件
    private File regionFile;
    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }
    //注入service
    @Resource
    private IRegionService regionService;

    /**
     * 接收页面传递过来的文件
     * @return 无页面返回
     */
    public String fileUpload() throws IOException {
        List<Region> regions = new ArrayList<>();
        String flag = "1";
        try {
            //读取页面传递过来的文件 并获取文件中的信息
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    //如果是第一行也就是标题行的话 那么就不进行读取
                    continue;
                }
                //读取前五列数据 并保存到对象中 代码重用性不高
                String id = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String  city = row.getCell(2).getStringCellValue();
                String  district = row.getCell(3).getStringCellValue();
                String  postcode = row.getCell(4).getStringCellValue();
                Region region = new Region(id, province, city, district, postcode, null, null, null);
                regions.add(region);
            }
            regionService.saveSubarea(regions);

        } catch (IOException e) {
            e.printStackTrace();
            flag = "0";
        }
        //将flag写入页面 表示读取是否成功
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(flag);

        return NONE;
    }
}
