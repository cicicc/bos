package com.afeng.bos.service.impl;

import com.afeng.bos.dao.ISubareaDao;
import com.afeng.bos.domain.Subarea;
import com.afeng.bos.service.ISubareaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

    //注入dao层对象
    @Resource
    private ISubareaDao subareaDao;

    /**
     * 调用dao层进行区域的保存
     * @param subarea 要保存的分区对象
     */
    @Override
    public void add(Subarea subarea) {
        subareaDao.save(subarea);
    }
}
