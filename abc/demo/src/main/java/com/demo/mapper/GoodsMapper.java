package com.demo.mapper;

import com.demo.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 123
 * @since 2023-11-17
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> selectPage(Integer pageNum, Integer pageSize, String name,String goodstype,String storage1);
    Integer selectTotal(String name,String goodstype,String storage1);





}
