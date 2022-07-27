package com.dormitory_springboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    public static String createDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
}
