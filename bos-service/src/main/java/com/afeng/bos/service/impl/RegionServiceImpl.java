package com.afeng.bos.service.impl;

import com.afeng.bos.dao.IRegionDao;
import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Region;
import com.afeng.bos.service.IRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service
public class RegionServiceImpl  implements IRegionService {

    //注入regionService
    @Resource
    private IRegionDao regionDao ;


    /**
     * 保存页面传递过来的分区信息
     * @param regions 传递过来的分区信息
     */
    @Override
    public void saveSubarea(List<Region> regions) {
        for (Region region : regions) {
            regionDao.saveOrUpdate(region);
        }
    }

    /**
     * 根据pageBean去数据库中查询数据 limit?,?
     * @param pageBean 封装了查询数据的pageBean对象
     */
    @Override
    @Transactional(readOnly = true)
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findByq(String q) {

        return regionDao.findByQ(q);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return regionDao.findAll();
    }

}
