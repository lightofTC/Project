package com.ada.marketbackend.user.service;

import com.ada.marketbackend.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-05
 */
public interface IUserService extends IService<User> {
    User loginCheck(String userName,String userPass);
    int regAdd(String userName,String userPass);
    User getUser(String userName);
    int resetPass(String userName,String userPass,String newUserPass);
    int resetUserInfo(String userName,String newName,String userPhone);
}