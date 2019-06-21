package com.lingzst.webo2o.service;

import com.lingzst.webo2o.dto.ShopExecution;
import com.lingzst.webo2o.entity.Shop;

import java.io.InputStream;


public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);

    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName);

    Shop getByShopId(long shopId);

}
