package com.ada.marketbackend.province_avprice.service.impl;

import com.ada.marketbackend.province_avprice.entity.ProvinceAvprice;
import com.ada.marketbackend.province_avprice.mapper.ProvinceAvpriceMapper;
import com.ada.marketbackend.province_avprice.service.IProvinceAvpriceService;
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
 * @since 2020-08-06
 */
@Service
public class ProvinceAvpriceServiceImpl extends ServiceImpl<ProvinceAvpriceMapper, ProvinceAvprice> implements IProvinceAvpriceService {
    @Autowired
    private ProvinceAvpriceMapper provinceAvpriceMapper;
    @Override
    public List<ProvinceAvprice> getProvinceAvpriceByProduct(String productName) {
        return provinceAvpriceMapper.getProvinceAvpriceByProduct(productName);
    }
}
