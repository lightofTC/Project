package com.ada.marketbackend.forecast_price.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("forecast_price")
public class ForecastPrice implements Serializable {

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
     * 预测时间
     */
    private String date;

    /**
     * 预测价格
     */
    private Double forprice;


}
