package com.ada.marketbackend.province.service;

import com.ada.marketbackend.province.entity.Province;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-06
 */
public interface IProvinceService extends IService<Province> {
    // 获取省份列表
    List<Province> getProvinceList();
}
