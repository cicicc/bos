package com.afeng.bos.web.action;

import com.afeng.bos.domain.Region;

import java.io.File;

public class RegionAction  extends BaseAction<Region>{

    //接收页面传递过来的文件
    private File regionFile;

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    /**
     * 接收页面传递过来的文件
     * @return 无页面返回
     */
    public String fileUpload() {
        System.out.println(regionFile);
        return NONE;
    }
}
