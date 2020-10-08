package com.ada.marketbackend.type.mapper;

import com.ada.marketbackend.type.entity.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-11
 */
public interface TypeMapper extends BaseMapper<Type> {
    @Select("select count(distinct typeID) as result from price, product_type where price.productID=product_type.productID and date=(select max(date) from price)")
    List<Map<String,Object>> getTypeNumber();
}
