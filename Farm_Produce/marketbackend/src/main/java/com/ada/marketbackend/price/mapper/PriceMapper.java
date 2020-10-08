package com.ada.marketbackend.price.mapper;

import com.ada.marketbackend.price.entity.Price;
import com.ada.marketbackend.product.entity.Product;
import com.ada.marketbackend.market.entity.Market;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-06
 */
public interface PriceMapper extends BaseMapper<Price> {

    @Select("select date, price " +
            "from product, market, price " +
            "where market.marketID = price.marketID " +
            "      and price.productID = product.productID " +
            "      and marketName = #{marketName} " +
            "      and productName = #{productName}")
    List<Price> getPriceByMarketAndProduct(@Param("marketName") String marketName, @Param("productName") String productName);

    @Select("select marketName, price " +
            "from price, market, product, province " +
            "where product.productID = price.productID " +
            "      and market.marketID = price.marketID " +
            "      and province.provinceID = market.provinceID " +
            "      and province.provinceName = #{provinceName} " +
            "      and product.productName = #{productName} " +
            "      and price.date = (select max(date) from price)")
    List<Price> getPriceByProvinceAndProduct(@Param("provinceName") String provinceName, @Param("productName") String productName);

    @Select("select productName as product, provinceName as province, marketName as market, price, date " +
            "from price, product, market, province " +
            "where price.productID = product.productID " +
            "      and price.marketID = market.marketID " +
            "      and market.provinceID = province.provinceID " +
            "      and date=(select max(date) from price)")
    List<Map<String,Object>> getLatestPrice();

    @Select("select count(*) as result from price where date=(select max(date) from price)")
    List<Map<String,Object>> getTotalDataVolume();
}
