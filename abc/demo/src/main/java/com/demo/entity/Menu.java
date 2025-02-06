package com.demo.entity;

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
 * @since 2023-11-15
 */
@Data

public class Menu implements Serializable {

    private Integer id;
    private String menucode;
    private String menuname;
    private String menulevel;
    private String menuparentcode;
    private String menuclick;
    private String menuright;
    private String menucomponent;
    private String menuicon;


}
