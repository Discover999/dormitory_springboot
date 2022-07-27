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
public class Absent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer buildingId;

    private Integer dormitoryId;

    private Integer studentId;

    private Integer dormitoryAdminId;

    private String createDate;

    private String reason;


}
