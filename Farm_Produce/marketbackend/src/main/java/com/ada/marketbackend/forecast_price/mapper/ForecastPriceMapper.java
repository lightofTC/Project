package com.ada.marketbackend.forecast_price.mapper;

import com.ada.marketbackend.forecast_price.entity.ForecastPrice;
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
 * @since 2020-08-16
 */
public interface ForecastPriceMapper extends BaseMapper<ForecastPrice> {
    @Select("select price,forprice,price.date " +
            "from market,price,forecast_price " +
            "where market.marketID=#{marketID} " +
            "   and price.marketID=#{marketID} " +
            "   and forecast_price.marketID=#{marketID} " +
            "   and price.productID=#{productID} " +
            "   and forecast_price.productID=#{productID}" +
            "   and price.date=forecast_price.date ")
    List<Map<String,Object>> getForecastPriceByMarketAndProduct(@Param("marketID") String marketID, @Param("productID") String productID);

    @Select("select `date`,forprice " +
            "from forecast_price " +
            "where marketID=#{marketID} " +
            "   and productID=#{productID} " +
            "   and `date`=#{date} ")
    List<Map<String,Object>> getForecastPriceByMarketAndProductAndDate(@Param("marketID") String marketID, @Param("productID") String productID,@Param("date")String date);
}
