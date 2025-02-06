package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select *from menu where menuRight like #{roleId}")
    List<Menu>list(String roleId );
}
