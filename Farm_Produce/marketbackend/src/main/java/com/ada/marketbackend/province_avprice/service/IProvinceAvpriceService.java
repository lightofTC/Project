package com.ada.marketbackend.province_avprice.service;

import com.ada.marketbackend.province_avprice.entity.ProvinceAvprice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-06
 */
public interface IProvinceAvpriceService extends IService<ProvinceAvprice> {
    // 获取名为productName农产品各省平均价格列表
    List<ProvinceAvprice> getProvinceAvpriceByProduct(@Param("productName") String productName);
}
