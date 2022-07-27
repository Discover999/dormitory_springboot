package com.dormitory_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2022-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Dormitory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer buildingId;

    private String name;

    private Integer type;

    private Integer available;

    private String telephone;


}
