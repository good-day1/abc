package com.demo.mapper;

import com.demo.entity.Buy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.RecordRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 123
 * @since 2023-11-28
 */
@Mapper
public interface BuyMapper extends BaseMapper<Buy> {
    List<RecordRes> selectPage1(Integer pageNum, Integer pageSize, String name, String goodstype, String storage1,
                                String userId1);
    Integer selectTotal1(String name,String goodstype,String storage1,String userId1);

    List<RecordRes> selectPage(Integer pageNum, Integer pageSize, String name, String goodstype, String storage1,String userId1);
    Integer selectTotal(String name,String goodstype,String storage1);
}
