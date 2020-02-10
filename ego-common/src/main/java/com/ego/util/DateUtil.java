package com.ego.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jick on 2019/3/26.
 */
public class DateUtil {
    public  static   String    pattern_date = "yyyy/MM/dd";

    public   static   String    getDateStr(LocalDateTime  date,String pattern){
        DateTimeFormatter  dtf  =  DateTimeFormatter.ofPattern(pattern);
        return    dtf.format(date);
    }


    //测试
    public static void main(String[] args) {
        String  dateStr  =  DateUtil.getDateStr(LocalDateTime.now(),DateUtil.pattern_date);
        System.out.println(dateStr);
    }

}
