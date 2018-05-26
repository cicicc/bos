package com.afeng.bos.service.impl;

import com.afeng.bos.dao.ISubareaDao;
import com.afeng.bos.domain.PageBean;
import com.afeng.bos.domain.Subarea;
import com.afeng.bos.service.ISubareaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 查询所有的分区信息 并返回list集合
     * @return 返回分区的list集合
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subarea> findAll() {

        return null;
    }

    /**
     * 查询数据库中所有的数据数目
     * @return 查询到的subarea数目
     */
    @Override
    @Transactional(readOnly = true)
    public int gedCount() {

        return subareaDao.getCount();
    }

    /**
     * 根据pageBean1中的参数进行相对应的查询 并把数据封装到pageBean中去
      * @param pageBean 封装参数的pageBean对象
     */
    @Override
    @Transactional(readOnly = true)
    public void pageQuery(PageBean pageBean) {

    }


}
