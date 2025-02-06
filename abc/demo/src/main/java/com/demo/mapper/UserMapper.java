package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where name like #{name} and role_id=#{roleId}  limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String name,Integer roleId);

    @Select("select count(*) from user where name like concat('%', #{name}, '%') and role_id=#{roleId} ")
    Integer selectTotal(String name,Integer roleId);
}
