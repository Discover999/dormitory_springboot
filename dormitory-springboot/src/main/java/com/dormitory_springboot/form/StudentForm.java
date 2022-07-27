package com.dormitory_springboot.form;

import lombok.Data;

@Data
public class StudentForm {
    private Integer id;
    private String number;
    private String name;
    private String gender;
    private Integer dormitoryId;
    private Integer oldDormitoryId;
    private String state;
    private String createDate;
}
