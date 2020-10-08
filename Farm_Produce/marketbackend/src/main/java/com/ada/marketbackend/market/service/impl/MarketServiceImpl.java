package com.ada.marketbackend.market.service.impl;

import com.ada.marketbackend.market.entity.Market;
import com.ada.marketbackend.market.mapper.MarketMapper;
import com.ada.marketbackend.market.service.IMarketService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MarketServiceImpl extends ServiceImpl<MarketMapper, Market> implements IMarketService {
    @Autowired
    private MarketMapper marketMapper;
    @Override
    public List<Market> getMarketByProvinceName(String provinceName) {
        return marketMapper.getMarketByProvinceName(provinceName);
    }

    @Override
    public List<Market> getMarketByProvinceID(String provinceID) {
        return marketMapper.getMarketByProvinceID(provinceID);
    }

    @Override
    public List<Map<String,Object>> getMarketNoByProvince() {
        return marketMapper.getMarketNoByProvince();
    }

    @Override
    public List<Map<String, Object>> getLatestMarketNumber() {
        return marketMapper.getLatestMarketNumber();
    }

    @Override
    public List<Map<String,Object>> getMarketByProvince() {
        return marketMapper.getMarketByProvince();
    }

}
