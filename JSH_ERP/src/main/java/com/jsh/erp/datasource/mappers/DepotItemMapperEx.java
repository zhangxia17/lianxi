package com.jsh.erp.datasource.mappers;

import com.jsh.erp.datasource.entities.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @Author: cjl
 * @Date: 2019/1/24 16:59
 */
public interface DepotItemMapperEx {
    List<DepotItem> selectByConditionDepotItem(
            @Param("name") String name,
            @Param("type") Integer type,
            @Param("remark") String remark,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsByDepotItem(
            @Param("name") String name,
            @Param("type") Integer type,
            @Param("remark") String remark);

    List<DepotItemVo4HeaderId> getHeaderIdByMaterial(
            @Param("materialParam") String materialParam,
            @Param("depotIds") String depotIds);

    List<DepotItemVo4DetailByTypeAndMId> findDetailByTypeAndMaterialIdList(
            @Param("mId") Long mId,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long findDetailByTypeAndMaterialIdCounts(
            @Param("mId") Long mId);

    List<DepotItemVo4Material> findStockNumByMaterialIdList(
            @Param("mId") Long mId,
            @Param("monthTime") String monthTime,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long findStockNumByMaterialIdCounts(
            @Param("mId") Long mId,
            @Param("monthTime") String monthTime);

    int findByTypeAndMaterialIdIn(
            @Param("mId") Long mId);

    int findByTypeAndMaterialIdOut(
            @Param("mId") Long mId);

    List<DepotItemVo4WithInfoEx> getDetailList(
            @Param("headerId") Long headerId);

    List<DepotItemVo4WithInfoEx> findByAll(
            @Param("headIds") String headIds,
            @Param("materialIds") String materialIds,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    int findByAllCount(
            @Param("headIds") String headIds,
            @Param("materialIds") String materialIds);

    BigDecimal findByTypeInIsPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findByTypeInIsNotPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findByTypeOutIsPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findByTypeOutIsNotPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);



    BigDecimal findPriceByTypeInIsPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findPriceByTypeInIsNotPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findPriceByTypeOutIsPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findPriceByTypeOutIsNotPrev(
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findAssembleIsPrev(
            @Param("subType") String subType,
            @Param("mType") String mType,
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal findAssembleIsNotPrev(
            @Param("subType") String subType,
            @Param("mType") String mType,
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime);

    BigDecimal buyOrSaleNumber(
            @Param("type") String type,
            @Param("subType") String subType,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime,
            @Param("sumType") String sumType);

    BigDecimal buyOrSalePrice(
            @Param("type") String type,
            @Param("subType") String subType,
            @Param("MId") Long MId,
            @Param("MonthTime") String MonthTime,
            @Param("sumType") String sumType);

    BigDecimal findGiftByTypeIn(
            @Param("subType") String subType,
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId);

    BigDecimal findGiftByTypeOut(
            @Param("subType") String subType,
            @Param("ProjectId") Integer ProjectId,
            @Param("MId") Long MId);
    /**
     * create by: cjl
     * description:
     *  通过单据主表id查询所有单据子表数据
     * create time: 2019/1/24 16:56
     * @Param: depotheadId
     * @return java.util.List<com.jsh.erp.datasource.entities.DepotItem>
     */
     List<DepotItem> findDepotItemListBydepotheadId(@Param("depotheadId")Long depotheadId,
                                                    @Param("enableSerialNumber")String enableSerialNumber);
     /**
      * 根据单据主表id删除单据子表数据
      * 物理删除，已弃用
      * */
     @Deprecated
     int deleteDepotItemByDepotHeadIds(@Param("depotheadIds")Long []depotHeadIds);
     /**
      * 根据单据主表id删除单据子表数据
      * */
     int batchDeleteDepotItemByDepotHeadIds(@Param("depotheadIds")Long []depotHeadIds);

    int batchDeleteDepotItemByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);
}
