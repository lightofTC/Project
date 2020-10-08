package com.ada.marketbackend.price_trend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author JI QIRUI
 * @since 2020-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PriceTrend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId("productID")
    private String productID;

    /**
     * 日期
     */
    private String date;

    /**
     * 最低价
     */
    private Double lowest;

    /**
     * 平均价
     */
    private Double average;

    /**
     * 最高价
     */
    private Double highest;


}
