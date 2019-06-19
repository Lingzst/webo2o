package com.lingzst.webo2o.dao;

import com.lingzst.webo2o.BaseTest;
import com.lingzst.webo2o.entity.Area;
import com.lingzst.webo2o.entity.Shop;
import com.lingzst.webo2o.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryByShopId() {
        long shopId = 39;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop.getArea().getAreaName());
    }

    @Test
    public void testAInsertShop() throws Exception {
        Shop shop = new Shop();
        shop.setOwnerId(1);
        Area area = new Area();
        area.setAreaId(1);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shop.setShopName("mytest1");
        shop.setShopDesc("mytest1");
        shop.setShopAddr("testaddr1");
        shop.setPhone("13810524526");
        shop.setShopImg("test1");
        shop.setLongitude(1D);
        shop.setLatitude(1D);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        shop.setArea(area);
        shop.setShopCategory(sc);
        int effectedNum = shopDao.insertShop(shop);
        Assert.assertEquals(1, effectedNum);
    }
    @Test
    public void testDUpdateShop() {
        long shopId = 1;
        Shop shop = new Shop();
        shop.setShopName("fejofoefo");
        shop.setEnableStatus(1);
        shop.setShopId(1L);
        shop.setOwnerId(1);
        shop.setShopId(39L);
        Area area = new Area();
        area.setAreaId(1);
        shop.setArea(area);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shop.setShopCategory(shopCategory);
        shop.setShopName("四季花");
        int effectedNum = shopDao.updateShop(shop);
        Assert.assertEquals(1, effectedNum);
    }
}
