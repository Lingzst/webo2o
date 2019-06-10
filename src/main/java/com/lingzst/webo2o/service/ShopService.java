package com.lingzst.webo2o.service;

import com.lingzst.webo2o.dto.ShopExecution;
import com.lingzst.webo2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;


public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);

}
