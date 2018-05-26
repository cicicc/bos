package com.afeng.bos.service;

import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Subarea;

import java.util.List;

public interface ISubareaService {
    void add(Subarea model);

    List<Subarea> findAll();

    int gedCount();

    void pageQuery(PageBean pageBean);
}
