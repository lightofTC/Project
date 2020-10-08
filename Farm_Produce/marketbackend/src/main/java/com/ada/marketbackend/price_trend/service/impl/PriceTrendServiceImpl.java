package com.ada.marketbackend.price_trend.service.impl;

import com.ada.marketbackend.price_trend.entity.PriceTrend;
import com.ada.marketbackend.price_trend.mapper.PriceTrendMapper;
import com.ada.marketbackend.price_trend.service.IPriceTrendService;
import com.ada.marketbackend.product.entity.Product;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-07
 */
@Service
public class PriceTrendServiceImpl extends ServiceImpl<PriceTrendMapper, PriceTrend> implements IPriceTrendService {
    @Autowired
    private PriceTrendMapper priceTrendMapper;

    @Override
    public List<PriceTrend> getPriceTrendByProductID(String productID){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("date","lowest","average","highest");
        wrapper.eq("productID",productID);
        List<PriceTrend> priceTrendList = priceTrendMapper.selectList(wrapper);
        return priceTrendList;
    }


}
