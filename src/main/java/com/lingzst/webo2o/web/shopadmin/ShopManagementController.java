package com.lingzst.webo2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingzst.webo2o.dto.ShopExecution;
import com.lingzst.webo2o.entity.Area;
import com.lingzst.webo2o.entity.PersonInfo;
import com.lingzst.webo2o.entity.Shop;
import com.lingzst.webo2o.entity.ShopCategory;
import com.lingzst.webo2o.enums.ShopStateEnum;
import com.lingzst.webo2o.service.AreaService;
import com.lingzst.webo2o.service.ShopCategoryService;
import com.lingzst.webo2o.service.ShopService;
import com.lingzst.webo2o.util.CodeUtil;
import com.lingzst.webo2o.util.HttpServletRequestUtil;
import com.lingzst.webo2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            //Session TODO
            owner.setUserId(1L);
            shop.setOwnerId(1);
//            File shopImgFile = new File(PathUtil.getImgBasePath() + "randomfile for test");
//            try {
//                shopImgFile.createNewFile();
//            } catch (IOException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", "createNewFile fail");
//                return modelMap;
//            }
//            try {
//                inputStreamToFile(shopImg.getInputStream(), shopImgFile);
//            } catch (IOException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", "inputStreamToFile fail");
//                return modelMap;
//            }
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
            if (se.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("sucess", false);
                modelMap.put("errMsg", se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

//    private static void inputStreamToFile(InputStream ins, File file) {
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = ins.read(buffer)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
//        } finally {
//            try {
//                if (os != null) {
//                    os.close();
//                }
//                if (ins != null) {
//                    ins.close();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException("inputStreamToFile产生异常：" + e.getMessage());
//            }
//        }
//    }
}
