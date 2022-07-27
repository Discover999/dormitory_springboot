package com.dormitory_springboot.vo;

import lombok.Data;

@Data
public class StudentVO {
    private Integer id;
    private String number;
    private String name;
    private String gender;
    private String dormitoryName;
    private String state;
    private String createDate;
}
