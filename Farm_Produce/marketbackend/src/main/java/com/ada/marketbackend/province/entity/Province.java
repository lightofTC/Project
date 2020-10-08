package com.ada.marketbackend.province.entity;

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
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 省ID
     */
    @TableId("provinceID")
    private String provinceID;

    /**
     * 省份名
     */
    @TableField("provinceName")
    private String provinceName;


}
