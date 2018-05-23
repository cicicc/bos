package com.afeng.bos.service;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Region;

import java.util.List;

public interface IRegionService {
    void saveSubarea(List<Region> regions);

    void pageQuery(PageBean pageBean);

    List<Region> findByq(String q);

    List<Region> findByq();
}
