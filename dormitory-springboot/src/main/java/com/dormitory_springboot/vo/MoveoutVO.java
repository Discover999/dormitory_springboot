package com.dormitory_springboot.vo;

import lombok.Data;

@Data
public class MoveoutVO {
    private Integer id;
    private String studentName;
    private String dormitoryName;
    private String reason;
    private String createDate;
}
