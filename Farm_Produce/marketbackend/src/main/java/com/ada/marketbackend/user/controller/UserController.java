package com.ada.marketbackend.user.controller;


import com.ada.marketbackend.user.entity.User;
import com.ada.marketbackend.user.service.IUserService;
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
 * @since 2020-08-05
 */
@CrossOrigin(origins={DEVELOP_ORIGIN, PRODUCE_ORIGIN}, allowCredentials="true")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/loginCheck",method = RequestMethod.POST)
    public String loginCheck(String userName,String userPass){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            User user=userService.loginCheck(userName,userPass);
            if(user!=null){
                map.put("status","200");
                map.put("data",user);
            }
            else {
                map.put("status","500");
                map.put("errorMsg","Fail");
            }
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/regAdd",method = RequestMethod.POST)
    public String regAdd(String userName,String userPass){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            int flag=userService.regAdd(userName,userPass);
            if(flag==1){
                map.put("status","200");
                map.put("data","Success");
            }
            else if(flag==0){
                map.put("status","500");
                map.put("errorMsg","用户名已存在");
            }
            else {
                map.put("status","500");
                map.put("errorMsg","注册失败");
            }
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/getUser",method = RequestMethod.POST)
    public String getUser(String userName){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            User user=userService.getUser(userName);
            if(user!=null){
                map.put("status","200");
                map.put("data",user);
            }
            else {
                map.put("status","500");
                map.put("errorMsg","Fail");
            }
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/resetPass",method = RequestMethod.POST)
    public String resetPass(String userName,String userPass, String newUserPass){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            int flag=userService.resetPass(userName,userPass,newUserPass);
            if(flag==1){
                map.put("status","200");
                map.put("msg","Success");
            }
            else if(flag==0){
                map.put("status","500");
                map.put("errorMsg","原密码错误！请重新输入原密码");
            }
            else {
                map.put("status","500");
                map.put("errorMsg","系统错误");
            }
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value="/resetUserInfo",method = RequestMethod.POST)
    public String resetUserInfo(String userName,String newName, String userPhone){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            int flag=userService.resetUserInfo(userName,newName,userPhone);
            if(flag==1){
                map.put("status","200");
                map.put("msg","Success");
            }
            else {
                map.put("status","500");
                map.put("errorMsg","系统错误");
            }
        }
        catch(Exception ex){
            map.put("status","500");
            map.put("errorMsg","Error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

}