package com.lingzst.webo2o.service;

import com.lingzst.webo2o.BaseTest;
import com.lingzst.webo2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        Assert.assertEquals("北京", areaList.get(0).getAreaName());
    }
}
