package com.ada.marketbackend.product.service;

import com.ada.marketbackend.product.entity.Product;
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
public interface IProductService extends IService<Product> {
    // 获取农产品列表
    List<Product> getProductList();

    List<Map<String,Object>> getProductNoByType(String marketName, String type);
    List<Map<String,Object>> getProductsByType(String marketName, String type);
    List<Map<String,Object>> getTypePercents(@Param("marketName") String marketName, @Param("type") String type);

    // 获取实时监测中品种数量
    List<Map<String,Object>> getLatestProductNumber();
    // 根据市场ID查询农产品列表
    List<Map<String,Object>> getProductListByMarketID(@Param("marketID") String marketID);
    // 根据市场名查询农产品列表
    List<Map<String,Object>> getProductListByMarketName(@Param("marketName") String marketName);
}
