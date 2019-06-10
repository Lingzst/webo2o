package com.lingzst.webo2o.dao;

import com.lingzst.webo2o.BaseTest;
import com.lingzst.webo2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        Assert.assertEquals(2, areaList.size());
    }
}
