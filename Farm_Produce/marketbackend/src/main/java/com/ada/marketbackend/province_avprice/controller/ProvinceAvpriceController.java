package com.ada.marketbackend.province_avprice.controller;


import com.ada.marketbackend.province_avprice.entity.ProvinceAvprice;
import com.ada.marketbackend.province_avprice.service.IProvinceAvpriceService;
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
@RequestMapping("/province_avprice")
public class ProvinceAvpriceController {
    @Autowired
    private IProvinceAvpriceService provinceAvpriceService;

    @RequestMapping(value = "/getProvinceAvpriceByProduct", method = RequestMethod.POST)
    public String getProvinceAvpriceByProduct(String productName){
        Map<String, Object> map = new HashMap<>();
        try{
            List<ProvinceAvprice> provinceAvpriceList = provinceAvpriceService.getProvinceAvpriceByProduct(productName);
            map.put("status", 200);
            map.put("data", provinceAvpriceList);
        }catch (Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询错误: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
