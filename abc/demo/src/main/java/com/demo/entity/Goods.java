package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 123
 * @since 2023-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer storage;

    @TableField("goodsType")
    private Integer goodstype;

    private Integer count;

    private String remark;
    private Integer price;

}
