package com.ada.marketbackend.price_trend.service;

import com.ada.marketbackend.price_trend.entity.PriceTrend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JI QIRUI
 * @since 2020-08-07
 */
public interface IPriceTrendService extends IService<PriceTrend> {
    List<PriceTrend> getPriceTrendByProductID(String productID);
}
