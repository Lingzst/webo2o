package com.lingzst.webo2o.dao;

import com.lingzst.webo2o.BaseTest;
import com.lingzst.webo2o.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategoryDao() {
        List<ShopCategory>  shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        Assert.assertEquals(1, shopCategoryList.size());
    }
}
