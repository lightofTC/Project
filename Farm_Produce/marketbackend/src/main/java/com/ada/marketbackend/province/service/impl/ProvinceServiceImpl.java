package com.ada.marketbackend.province.service.impl;

import com.ada.marketbackend.province.entity.Province;
import com.ada.marketbackend.province.mapper.ProvinceMapper;
import com.ada.marketbackend.province.service.IProvinceService;
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
 * @since 2020-08-06
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements IProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public List<Province> getProvinceList() {
        QueryWrapper wrapper = new QueryWrapper();
        List<Province> provincesList = provinceMapper.selectList(wrapper);
        return provincesList;
    }
}
