package com.ada.marketbackend.province_avprice.entity;

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
public class ProvinceAvprice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 省ID
     */
    @TableId("provinceID")
    private String provinceID;

    /**
     * 产品ID
     */
    @TableField("productID")
    private String productID;

    /**
     * 平均价格
     */
    private Double value;

    /**
     * 日期
     */
    private String date;

    // 自行添加的属性
    private String name;
}
