package com.ada.marketbackend.market.entity;

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
public class Market implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 市场ID
     */
    @TableId("marketID")
    private String marketID;

    /**
     * 市场名称
     */
    @TableField("marketName")
    private String marketName;

    /**
     * 省份ID
     */
    @TableField("provinceID")
    private String provinceID;

    /**
     * 市ID
     */
    @TableField("cityID")
    private String cityID;


}
