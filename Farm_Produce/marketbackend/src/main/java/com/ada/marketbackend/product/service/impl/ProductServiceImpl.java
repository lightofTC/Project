package com.ada.marketbackend.product.service.impl;

import com.ada.marketbackend.product.entity.Product;
import com.ada.marketbackend.product.mapper.ProductMapper;
import com.ada.marketbackend.product.service.IProductService;
import com.ada.marketbackend.province.service.IProvinceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> getProductList() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("productID", "productName");
        List<Product> productList = productMapper.selectList(wrapper);
        return productList;
    }

    @Override
    public List<Map<String,Object>> getProductNoByType(String marketName, String type)
    {
        return productMapper.getProductNoByType(marketName,type);
    }

    @Override
    public List<Map<String,Object>> getProductsByType(String marketName, String type)
    {
        return productMapper.getProductsByType(marketName,type);
    }

    @Override
    public List<Map<String, Object>> getTypePercents(String marketName, String type) {
        return productMapper.getTypePercents(marketName, type);
    }

    @Override
    public List<Map<String, Object>> getLatestProductNumber() {
        return productMapper.getLatestProductNumber();
    }

    @Override
    public List<Map<String, Object>> getProductListByMarketID(String marketID) {
        return productMapper.getProductListByMarketID(marketID);
    }

    @Override
    public List<Map<String, Object>> getProductListByMarketName(String marketName) {
        return productMapper.getProductListByMarketName(marketName);
    }


}
