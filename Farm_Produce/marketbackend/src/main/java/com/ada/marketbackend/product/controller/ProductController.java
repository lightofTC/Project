package com.ada.marketbackend.product.controller;


import com.ada.marketbackend.product.entity.Product;
import com.ada.marketbackend.product.service.IProductService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.ada.marketbackend.utils.Constant.DEVELOP_ORIGIN;
import static com.ada.marketbackend.utils.Constant.PRODUCE_ORIGIN;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-06
 */
@CrossOrigin(origins={DEVELOP_ORIGIN, PRODUCE_ORIGIN}, allowCredentials="true")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/getProductList", method = RequestMethod.POST)
    public String getProductList(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Product> productList = productService.getProductList();
            map.put("status", 200);
            map.put("data", productList);
        }catch(Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询错误: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/getProductNoByType",method = RequestMethod.POST)
    public String getProductNoByType(String marketName,String type){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<Map<String,Object>> productList=productService.getProductNoByType(marketName,type);
            map.put("status","200");
            map.put("data",productList);
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/getProductsByType",method = RequestMethod.POST)
    public String getProductsByType(String marketName,String type){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<Map<String,Object>> productList=productService.getProductsByType(marketName,type);
            map.put("status","200");
            map.put("data",productList);
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/getTypePercents",method = RequestMethod.POST)
    public String getTypePercents(String marketName, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> productList = productService.getTypePercents(marketName, type);
            map.put("status", "200");
            map.put("data", productList);
        } catch (Exception ex) {
            map.put("status", "500");
            map.put("errorMsg", "Error:" + ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/getLatestProductNumber",method = RequestMethod.POST)
    public String getLatestProductNumber() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> productNum = productService.getLatestProductNumber();
            map.put("status", "200");
            map.put("data", productNum);
        } catch (Exception ex) {
            map.put("status", "500");
            map.put("errorMsg", "Error:" + ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getProductListByMarketID",method = RequestMethod.POST)
    public String getProductListByMarketID(String marketID){
        Map<String, Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> productList = productService.getProductListByMarketID(marketID);
            map.put("status","200");
            map.put("data",productList);
        }catch (Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getProductListByMarketName",method = RequestMethod.POST)
    public String getProductListByMarketName(String marketName){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> productList = productService.getProductListByMarketName(marketName);
            map.put("status","200");
            map.put("data",productList);
        }catch (Exception ex){
            map.put("status","500");
            map.put("errMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
