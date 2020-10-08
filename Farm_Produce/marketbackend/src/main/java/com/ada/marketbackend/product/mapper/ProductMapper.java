package com.ada.marketbackend.product.mapper;

import com.ada.marketbackend.product.entity.Product;
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
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT concat(month(a.click_date),'-',day(a.click_date)) as date, ifnull(b.number,0) as value " +
            "FROM (SELECT CURDATE() as click_date " +
            "      UNION ALL " +
            "      SELECT DATE_SUB(CURDATE(),INTERVAL 1 day) as click_date " +
            "      UNION ALL " +
            "      SELECT DATE_SUB(CURDATE(),INTERVAL 2 day) as click_date " +
            "      UNION ALL " +
            "      SELECT DATE_SUB(CURDATE(),INTERVAL 3 day) as click_date " +
            "      UNION ALL " +
            "      SELECT DATE_SUB(CURDATE(),INTERVAL 4 day) as click_date " +
            "      UNION ALL " +
            "      SELECT DATE_SUB(CURDATE(),INTERVAL 5 day) as click_date " +
            "      UNION ALL " +
            "      SELECT DATE_SUB(CURDATE(),INTERVAL 6 day) as click_date) " +
            "a LEFT JOIN ( " +
            "      select price.date,count(*) as number " +
            "      from market, price, product_type, type " +
            "      where market.marketID=price.marketID " +
            "      and price.productID=product_type.productID " +
            "      and product_type.typeID=type.typeID " +
            "      and market.marketName=#{marketName} " +
            "      and type.typeName=#{type} " +
            "      and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= price.date " +
            "      group by price.date) " +
            "b ON a.click_date = b.date " +
            "order by a.click_date")
    List<Map<String,Object>> getProductNoByType(@Param("marketName") String marketName, @Param("type") String type);

    @Select("select pri.date,group_concat(pro.productName) as productName from market as m,price as pri,product as pro " +
            "where m.marketID=pri.marketID and pri.productID=pro.productID " +
            "and m.marketName=#{marketName} and pro.productType=#{type} " +
            "and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= pri.date group by pri.date")
    List<Map<String,Object>> getProductsByType(@Param("marketName") String marketName, @Param("type") String type);

    @Select("Select case typeName when #{type} then #{type} " +
            "else '其他' end as name, " +
            "count(*) as value " +
            "from product_type, type, (select distinct productID " +
            "                          from price, market " +
            "                          where price.marketID = market.marketID " +
            "                          and marketName = #{marketName}) as allproduct " +
            "where product_type.typeID = type.typeID " +
            "      and allproduct.productID = product_type.productID " +
            "group by case typeName when #{type} then #{type} " +
            "       else '其他' end")
    List<Map<String,Object>> getTypePercents(@Param("marketName") String marketName, @Param("type") String type);

    @Select("select count(distinct productID) as result from price where date=(select max(date) from price)")
    List<Map<String,Object>> getLatestProductNumber();

    @Select("select distinct product.productID,product.productName " +
            "from market,price,product " +
            "where market.marketID=#{marketID} " +
            "   and price.marketID=#{marketID} " +
            "   and product.productID=price.productID")
    List<Map<String,Object>> getProductListByMarketID(@Param("marketID") String marketID);

    @Select("select distinct product.productID,product.productName " +
            "from market,price,product " +
            "where market.marketName=#{marketName} " +
            "   and price.marketID=market.marketID " +
            "   and product.productID=price.productID")
    List<Map<String,Object>> getProductListByMarketName(@Param("marketName") String marketName);
}
