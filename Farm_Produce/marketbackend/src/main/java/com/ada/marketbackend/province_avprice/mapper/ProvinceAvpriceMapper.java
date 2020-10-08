package com.ada.marketbackend.province_avprice.mapper;

import com.ada.marketbackend.province_avprice.entity.ProvinceAvprice;
import com.ada.marketbackend.product.entity.Product;
import com.ada.marketbackend.province.entity.Province;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-06
 */
public interface ProvinceAvpriceMapper extends BaseMapper<ProvinceAvprice> {

    @Select("select province.provinceName as name, province_avprice.avprice as value " +
            "from product, province, province_avprice " +
            "where province_avprice.provinceID = province.provinceID " +
            "      and province_avprice.productID = product.productID " +
            "      and productName = #{productName}" +
            "      and date = (select max(date) from province_avprice)")
    List<ProvinceAvprice> getProvinceAvpriceByProduct(@Param("productName") String productName);
}
