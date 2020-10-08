package com.ada.marketbackend.type.service;

import com.ada.marketbackend.type.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-11
 */
public interface ITypeService extends IService<Type> {
    // 获取农产品种类列表
    List<Type> getTypeList();

    // 获取实时监测种类数量
    List<Map<String,Object>> getTypeNumber();
}
