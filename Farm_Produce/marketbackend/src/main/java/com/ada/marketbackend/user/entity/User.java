package com.ada.marketbackend.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class    User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("userID")
    private String userID;

    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;


    /**
     * 用户密码
     */
    @TableField("userPass")
    private String userPass;

    /**
     * 用户电话
     */
    @TableField("userPhone")
    private String userPhone;

    /**
     * 用户权限
     */
    @TableField("userPermission")
    private String userPermission;


    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}