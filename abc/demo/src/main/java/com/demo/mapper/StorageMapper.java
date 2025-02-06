package com.demo.mapper;

import com.demo.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
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
public interface StorageMapper extends BaseMapper<Storage> {
    @Select("select * from storage where name like #{name}  limit #{pageNum}, #{pageSize}")
    List<Storage> selectPage(Integer pageNum, Integer pageSize, String name);

    @Select("select count(*) from storage where name like concat('%', #{name}, '%')  ")
    Integer selectTotal(String name);

}
