package com.afeng.bos.service.impl;

import com.afeng.bos.dao.IRegionDao;
import com.afeng.bos.domain.Region;
import com.afeng.bos.service.IRegionService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Component
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
}
