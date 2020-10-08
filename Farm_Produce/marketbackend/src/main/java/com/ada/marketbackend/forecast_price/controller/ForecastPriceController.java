package com.ada.marketbackend.forecast_price.controller;


import com.ada.marketbackend.forecast_price.service.IForecastPriceService;
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
 * @since 2020-08-16
 */
@CrossOrigin(origins={DEVELOP_ORIGIN, PRODUCE_ORIGIN}, allowCredentials="true")
@RestController
@RequestMapping("/forecast_price")
public class ForecastPriceController {
    @Autowired
    private IForecastPriceService iForecastPriceService;

    @RequestMapping(value = "/getForecastPriceByMarketAndProduct",method = RequestMethod.POST)
    public String getForecastPriceByMarketAndProduct(String marketID,String productID){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> fpriceList = iForecastPriceService.getForecastPriceByMarketAndProduct(marketID,productID);
            map.put("status", "200");
            map.put("data", fpriceList);
        }catch (Exception e){
            map.put("status", "500");
            map.put("errorMsg", "查询错误: "+e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getForecastPriceByMarketAndProductAndDate",method = RequestMethod.POST)
    public String getForecastPriceByMarketAndProductAndDate(String marketID,String productID,String date){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> fpriceList = iForecastPriceService.getForecastPriceByMarketAndProductAndDate(marketID,productID,date);
            map.put("status", "200");
            map.put("data", fpriceList);
        }catch (Exception e){
            map.put("status", "500");
            map.put("errorMsg", "查询错误: "+e.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
