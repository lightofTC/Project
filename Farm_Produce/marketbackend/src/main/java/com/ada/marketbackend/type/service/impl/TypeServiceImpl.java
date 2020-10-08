package com.ada.marketbackend.type.service.impl;

import com.ada.marketbackend.type.entity.Type;
import com.ada.marketbackend.type.mapper.TypeMapper;
import com.ada.marketbackend.type.service.ITypeService;
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
 * @since 2020-08-11
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getTypeList() {
        QueryWrapper wrapper = new QueryWrapper();
        List<Type> typeList = typeMapper.selectList(wrapper);
        return typeList;
    }

    @Override
    public List<Map<String, Object>> getTypeNumber() {
        return typeMapper.getTypeNumber();
    }

}
