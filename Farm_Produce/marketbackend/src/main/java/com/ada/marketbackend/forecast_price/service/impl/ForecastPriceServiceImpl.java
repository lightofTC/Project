package com.ada.marketbackend.forecast_price.service.impl;

import com.ada.marketbackend.forecast_price.entity.ForecastPrice;
import com.ada.marketbackend.forecast_price.mapper.ForecastPriceMapper;
import com.ada.marketbackend.forecast_price.service.IForecastPriceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-16
 */
@Service
public class ForecastPriceServiceImpl extends ServiceImpl<ForecastPriceMapper, ForecastPrice> implements IForecastPriceService {

    @Autowired
    private ForecastPriceMapper forecastPriceMapper;


    @Override
    public List<Map<String, Object>> getForecastPriceByMarketAndProduct(String marketID, String productID) {
        return forecastPriceMapper.getForecastPriceByMarketAndProduct(marketID,productID);
    }

    @Override
    public List<Map<String, Object>> getForecastPriceByMarketAndProductAndDate(String marketID, String productID, String date) {
        return forecastPriceMapper.getForecastPriceByMarketAndProductAndDate(marketID, productID, date);
    }

}
