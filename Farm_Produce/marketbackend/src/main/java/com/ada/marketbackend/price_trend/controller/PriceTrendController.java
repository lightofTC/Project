package com.ada.marketbackend.price_trend.controller;


import com.ada.marketbackend.price_trend.entity.PriceTrend;
import com.ada.marketbackend.price_trend.service.IPriceTrendService;
import com.ada.marketbackend.product.entity.Product;
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
 * @author JI QIRUI
 * @since 2020-08-07
 */
@CrossOrigin(origins={DEVELOP_ORIGIN, PRODUCE_ORIGIN}, allowCredentials="true")
@RestController
@RequestMapping("/price_trend")
public class PriceTrendController {
    @Autowired
    private IPriceTrendService priceTrendService;

    @RequestMapping(value = "/getPriceTrend",method = RequestMethod.POST)
    public String getPriceTrend(String productID){
        Map<String,Object> map = new HashMap<>();
        try{
            List<PriceTrend> priceTrendList = priceTrendService.getPriceTrendByProductID(productID);
            map.put("status", 200);
            map.put("data", priceTrendList);
        }catch(Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询错误: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
