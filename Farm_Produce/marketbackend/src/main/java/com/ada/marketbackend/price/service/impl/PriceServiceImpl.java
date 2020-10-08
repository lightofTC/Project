package com.ada.marketbackend.price.service.impl;

import com.ada.marketbackend.price.entity.Price;
import com.ada.marketbackend.price.mapper.PriceMapper;
import com.ada.marketbackend.price.service.IPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-06
 */
@Service
public class PriceServiceImpl extends ServiceImpl<PriceMapper, Price> implements IPriceService {
    @Autowired
    private PriceMapper priceMapper;
    @Override
    public List<Price> getPriceByMarketAndProduct(String marketName, String productName) {
        return priceMapper.getPriceByMarketAndProduct(marketName, productName);
    }

    @Override
    public List<Price> getPriceByProvinceAndProduct(String provinceName, String productName) {
        return priceMapper.getPriceByProvinceAndProduct(provinceName, productName);
    }

    @Override
    public List<Map<String,Object>> getLatestPrice(int currentPage,int size) {
        PageHelper.startPage(currentPage,size);
        List<Map<String,Object>> latestPrice=priceMapper.getLatestPrice();
        return latestPrice;
    }

    @Override
    public List<Map<String, Object>> getTotalDataVolume() {
        return priceMapper.getTotalDataVolume();
    }
}
