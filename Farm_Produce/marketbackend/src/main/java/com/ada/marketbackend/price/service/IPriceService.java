package com.ada.marketbackend.price.service;

import com.ada.marketbackend.price.entity.Price;
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
 * @since 2020-08-06
 */
public interface IPriceService extends IService<Price> {
    // 获取marketName农产品市场productName农产品的所有时间的价格列表
    List<Price> getPriceByMarketAndProduct(@Param("marketName") String marketName, @Param("productName") String productName);

    // 获取省内价格对比数据
    List<Price> getPriceByProvinceAndProduct(@Param("provinceName") String provinceName, @Param("productName") String productName);

    // 获取最新价格数据
    List<Map<String,Object>> getLatestPrice(int currentPage,int size);

    // 获取实时监测中总数据量
    List<Map<String,Object>> getTotalDataVolume();
}
