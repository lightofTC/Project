package com.ada.marketbackend.market.service;

import com.ada.marketbackend.market.entity.Market;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wei Zhen
 * @since 2020-08-06
 */
public interface IMarketService extends IService<Market> {
    // 获取名称为provinceName省份的市场列表
    List<Market> getMarketByProvinceName(@Param("provinceName") String provinceName);

    // 获取ID为provinceID省份的市场列表
    List<Market> getMarketByProvinceID(@Param("provinceID") String provinceID);

    List<Map<String,Object>> getMarketByProvince();
    List<Map<String,Object>> getMarketNoByProvince();

    // 获取实时监控中批发市场数量
    List<Map<String,Object>> getLatestMarketNumber();
}
