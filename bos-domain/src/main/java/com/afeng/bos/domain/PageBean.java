package com.afeng.bos.domain;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 封装关于分页的相关属性
 */
public class PageBean<T> {
    private int currentPage;//当前页数
    private int pageSize;//每页显示的数据数目
    private DetachedCriteria detachedCriteria;//离线查询条件
    private int total;//总记录数
    private List<T> rows;//返回到页面的数据

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageBean() {
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", detachedCriteria=" + detachedCriteria +
                ", totalRecord=" + total +
                ", rows=" + rows +
                '}';
    }
}
