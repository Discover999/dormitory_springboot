package com.dormitory_springboot.vo;

import lombok.Data;

@Data
public class AbsentVO {
    private Integer id;
    private String buildingName;
    private String dormitoryName;
    private String studentName;
    private String dormitoryAdminName;
    private String createDate;
    private String reason;
}
