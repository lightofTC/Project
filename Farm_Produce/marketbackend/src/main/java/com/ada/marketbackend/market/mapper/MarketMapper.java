package com.ada.marketbackend.market.mapper;

import com.ada.marketbackend.market.entity.Market;
import com.ada.marketbackend.province.entity.Province;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liu Ruiqi
 * @since 2020-08-06
 */
public interface MarketMapper extends BaseMapper<Market> {

    @Select("select marketID, marketName " +
            "from market as m, province as p " +
            "where m.provinceID = p.provinceID and p.provinceName = #{provinceName}")
    List<Market> getMarketByProvinceName(@Param("provinceName") String provinceName);

    @Select("select marketID, marketName " +
            "from market as m " +
            "where m.provinceID = #{provinceID}")
    List<Market> getMarketByProvinceID(@Param("provinceID") String provinceID);

    @Select("select p.provinceName as name,count(*) as value from market as m,province as p where m.provinceID=p.provinceID group by p.provinceName")
    List<Map<String,Object>> getMarketNoByProvince();

    @Select("select p.provinceName,group_concat(marketName) as marketName from market as m,province as p where m.provinceID=p.provinceID group by p.provinceName")
    List<Map<String,Object>> getMarketByProvince();

    @Select("select count(distinct marketID) as result from price where date=(select max(date) from price)")
    List<Map<String,Object>> getLatestMarketNumber();
}
