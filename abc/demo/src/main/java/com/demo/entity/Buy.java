package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2023-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Buy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goods;

    @TableField("userId")
    private Integer userid;

    private LocalDateTime createtime;

    private String remark;

    private Integer count;


}
