package com.lingzst.webo2o.service;

import com.lingzst.webo2o.BaseTest;
import com.lingzst.webo2o.dto.ShopExecution;
import com.lingzst.webo2o.entity.Area;
import com.lingzst.webo2o.entity.Shop;
import com.lingzst.webo2o.entity.ShopCategory;
import com.lingzst.webo2o.enums.ShopStateEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.Date;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(39L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("C:\\Users\\Jaxier\\Pictures\\Saved Pictures\\ling.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "ling.jpg");
        System.out.println(shopExecution.getShop().getShopImg());
    }

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setOwnerId(1);
        Area area = new Area();
        area.setAreaId(1);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shop.setShopName("mytest1");
        shop.setShopDesc("mytest1");
        shop.setShopAddr("testaddr1");
        shop.setPhone("131311");
        shop.setShopImg("test1");
        shop.setLongitude(1D);
        shop.setLatitude(1D);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        shop.setArea(area);
        shop.setShopCategory(sc);
        File shopImg = new File("C:\\Users\\Jaxier\\Pictures\\Saved Pictures\\ling.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
        Assert.assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }

}
