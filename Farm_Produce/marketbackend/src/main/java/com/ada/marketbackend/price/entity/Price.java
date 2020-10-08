package com.ada.marketbackend.price.entity;

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
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId("productID")
    private String productID;

    /**
     * 市场ID
     */
    @TableField("marketID")
    private String marketID;

    /**
     * 价格
     */
    private Double price;

    /**
     * 日期
     */
    private String date;


    // 自行添加的属性
    private String marketName;
}
