package com.ada.marketbackend.province.controller;


import com.ada.marketbackend.province.entity.Province;
import com.ada.marketbackend.province.service.IProvinceService;
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
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @RequestMapping(value = "/getProvinceList", method = RequestMethod.POST)
    public String getProvinceList(){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            List<Province> provinceList = provinceService.getProvinceList();
            map.put("status", 200);
            map.put("data", provinceList);
        }catch (Exception e){
            map.put("status", 500);
            map.put("errorMsg", "查询出错: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
