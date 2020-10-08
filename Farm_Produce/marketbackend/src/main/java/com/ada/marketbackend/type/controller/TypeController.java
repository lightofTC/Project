package com.ada.marketbackend.type.controller;


import com.ada.marketbackend.type.entity.Type;
import com.ada.marketbackend.type.service.ITypeService;
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
 * @since 2020-08-11
 */
@CrossOrigin(origins={DEVELOP_ORIGIN, PRODUCE_ORIGIN}, allowCredentials="true")
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @RequestMapping(value = "/getTypeList", method = RequestMethod.POST)
    public String getTypeList(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Type> typeList = typeService.getTypeList();
            map.put("status", "200");
            map.put("data", typeList);
        }catch (Exception e){
            map.put("status", "500");
            map.put("errorMsg", "查询错误: " + e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/getTypeNumber", method = RequestMethod.POST)
    public String getTypeNumber(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> typeNumber = typeService.getTypeNumber();
            map.put("status", "200");
            map.put("data", typeNumber);
        }catch (Exception e){
            map.put("status", "500");
            map.put("errorMsg", "查询错误: "+e.getMessage());
        }
        return JSON.toJSONString(map);
    }
}
