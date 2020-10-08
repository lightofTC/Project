package com.ada.marketbackend.price.controller;


import com.ada.marketbackend.price.entity.Price;
import com.ada.marketbackend.price.service.IPriceService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private IPriceService priceService;

    @RequestMapping(value = "/getPriceByMarketAndProduct", method = RequestMethod.POST)
    public String getPriceByMarketAndProduct(String marketName, String productName){
        Map<String, Object> map = new HashMap<>();
        try{
            List<Price> priceList = priceService.getPriceByMarketAndProduct(marketName, productName);
            map.put("status", 200);
            map.put("data", priceList);
        }catch (Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询错误:" + e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getPriceByProvinceAndProduct", method = RequestMethod.POST)
    public String getPriceByProvinceAndProduct(String provinceName, String productName){
        Map<String, Object> map = new HashMap<>();
        try{
            List<Price> priceList = priceService.getPriceByProvinceAndProduct(provinceName, productName);
            map.put("status", 200);
            map.put("data", priceList);
        }catch (Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询错误: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getLatestPrice", method = RequestMethod.POST)
    public String getLatestPrice(int currentPage,int size){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> latestPriceList = priceService.getLatestPrice(currentPage,size);
            PageInfo<Map<String,Object>> latestPriceInfo = new PageInfo<>(latestPriceList);
            map.put("status", "200");
            map.put("data", latestPriceList);
            map.put("count",latestPriceInfo.getTotal());
        }catch (Exception e){
            map.put("status", "500");
            map.put("errorMsg", "查询错误: "+e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getTotalDataVolume", method = RequestMethod.POST)
    public String getTotalDataVolume(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> totalDataVolume = priceService.getTotalDataVolume();
            map.put("status","200");
            map.put("data",totalDataVolume);
        }catch (Exception e){
            map.put("status","500");
            map.put("errorMsg", "查询错误: "+e.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
