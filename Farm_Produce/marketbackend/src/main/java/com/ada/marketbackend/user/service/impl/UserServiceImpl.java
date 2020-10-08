package com.ada.marketbackend.user.service.impl;

import com.ada.marketbackend.user.entity.User;
import com.ada.marketbackend.user.mapper.UserMapper;
import com.ada.marketbackend.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loginCheck(String userName, String userPass) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userName", userName);
        wrapper.eq("userPass", userPass);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public int regAdd(String userName, String userPass) {
        User user = new User();
        QueryWrapper nameWrapper = new QueryWrapper();
        nameWrapper.eq("userName", userName);
        User existingUser = userMapper.selectOne(nameWrapper);
        if (existingUser != null) {
            return 0;//0代表userName存在
        } else {
            user.setUserName(userName);
            user.setUserPass(userPass);
            user.setUserPhone("");
            user.setUserPermission("1");
            int i = userMapper.insert(user);
            return i;
        }
    }

    @Override
    public User getUser(String userName) {
        User user = new User();
        QueryWrapper nameWrapper = new QueryWrapper();
        nameWrapper.eq("userName", userName);
        user = userMapper.selectOne(nameWrapper);
        return user;
    }

    @Override
    public int resetPass(String userName, String userPass, String newUserPass) {
        QueryWrapper nameWrapper = new QueryWrapper();
        nameWrapper.eq("userName", userName);
        nameWrapper.eq("userPass",userPass);
        User existingUser = userMapper.selectOne(nameWrapper);
        if (existingUser.getUserID() != null) {
                existingUser.setUserPass(newUserPass);
                userMapper.update(existingUser,nameWrapper);
                return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int resetUserInfo(String userName,String newName, String userPhone) {
        QueryWrapper nameWrapper = new QueryWrapper();
        nameWrapper.eq("userName", userName);
        User existingUser = userMapper.selectOne(nameWrapper);
        if (existingUser.getUserID() != null) {
            existingUser.setUserName(newName);
            existingUser.setUserPhone(userPhone);
            userMapper.update(existingUser,nameWrapper);
            return 1;
        } else {
            return 0;
        }
    }
}