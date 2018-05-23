package com.afeng.bos.web.action;

import com.afeng.bos.domain.Region;
import com.afeng.bos.service.IRegionService;
import com.afeng.bos.utils.PinYin4jUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
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
    //接收分页参数
    private int page;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private String q;

    public void setQ(String q) {
        this.q = q;
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
                //将数据封装到region对象中
                Region region = new Region(id, province, city, district, postcode, null, null, null);
                // 生成数据所对应的分区和短码 此处可以单独提取一个方法出来 放在util类中
                province = province.substring(0, province.length() - 1);
                city = city.substring(0, city.length() - 1);
                district = district.substring(0, district.length() - 1);
                String info = province + city + district;
                String[] headByString = PinYin4jUtils.getHeadByString(info);
                String shortcode = StringUtils.join(headByString);
                //城市编码---->>shijiazhuang
                String citycode = PinYin4jUtils.hanziToPinyin(city, "");

                region.setShortcode(shortcode);
                region.setCitycode(citycode);
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

    /**
     * 分页查询数据
     * @return  异步进行查询 无需进行相关页面的返回
     */
    public String pageQuery() throws IOException {
        //根据传递过来的参数创建pageBean和离线查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
        // 设置属性 包括当前页数 每页显示条数 以及离线查询条件
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        pageBean.setDetachedCriteria(detachedCriteria);
        //调用service层进行相关查询
        regionService.pageQuery(pageBean);
        //将所得到的数据使用json进行对应的转换
        String[] excludes = {"currentPage", "pageSize", "detachedCriteria"};
        String json = switchObjectToJson(excludes);
        //将数据放入response域中 写回页面
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(json);

        return NONE;
    }

    /**
     *  动态加载分区
     * @return ajax加载数据 所以并没有返回页面
     */
    public String listAjax(){
        List<Region> regionList = null;
        if (StringUtils.isNotBlank(q)) {
            //如果查询条件不为空的话
            List<Region> list = regionService.findByq(q);
        }else {
            //查询所有条件
            List<Region> list = regionService.findAll();

        }
        this.switchObjectToJson(new String[]{"subareas"});
        return NONE;
    }

}
