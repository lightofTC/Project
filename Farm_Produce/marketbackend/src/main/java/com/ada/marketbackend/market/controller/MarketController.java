package com.ada.marketbackend.market.controller;


import com.ada.marketbackend.market.entity.Market;
import com.ada.marketbackend.market.service.IMarketService;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private IMarketService marketService;

    @RequestMapping(value = "/getMarketByProvinceName", method = RequestMethod.POST)
    public String getMarketByProvinceName(String provinceName){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Market> marketList = marketService.getMarketByProvinceName(provinceName);
            map.put("status", 200);
            map.put("data", marketList);
        }catch (Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询错误: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getMarketByProvinceID",method = RequestMethod.POST)
    public String getMarketByProvinceID(String provinceID){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Market> marketList = marketService.getMarketByProvinceID(provinceID);
            map.put("status", 200);
            map.put("data", marketList);
        }catch (Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询错误: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }


    @RequestMapping(value="/getMarketNoByProvince",method = RequestMethod.POST)
    public String getMarketNoByProvince(){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<Map<String,Object>> marketList=marketService.getMarketNoByProvince();
            map.put("status","200");
            map.put("data",marketList);
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/getMarketByProvince",method = RequestMethod.POST)
    public String getMarketByProvince(){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<Map<String,Object>> marketList=marketService.getMarketByProvince();
            map.put("status","200");
            map.put("data",marketList);
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getLatestMarketNumber", method = RequestMethod.POST)
    public String getLatestMarketNumber(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> marketNum = marketService.getLatestMarketNumber();
            map.put("status","200");
            map.put("data", marketNum);
        }catch (Exception e){
            map.put("status", "500");
            map.put("errorMsg", "Error:"+e.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
