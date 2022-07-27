package com.dormitory_springboot.vo;

import lombok.Data;

@Data
public class DormitoryVO {
    private Integer id;
    private String buildingName;
    private String name;
    private Integer type;
    private Integer available;
    private String telephone;
}
