package com.demo.entity;

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
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String remark;


}
