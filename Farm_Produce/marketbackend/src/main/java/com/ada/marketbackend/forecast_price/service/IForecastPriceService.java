package com.ada.marketbackend.forecast_price.service;

import com.ada.marketbackend.forecast_price.entity.ForecastPrice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-16
 */
public interface IForecastPriceService extends IService<ForecastPrice> {

    List<Map<String,Object>> getForecastPriceByMarketAndProduct(String marketID,String productID);
    List<Map<String,Object>> getForecastPriceByMarketAndProductAndDate(String marketID, String productID, String date);
}
