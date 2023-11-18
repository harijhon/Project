/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author RazOnTheFloor
 */
public class DateConvertor {
    public static Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null; // Return null if the input utilDate is null
        }
        return new Date(utilDate.getTime());
    }
    public static String getAnnotationSimple(java.util.Date utilDate){
        if (utilDate == null) {
            return null; // Return null if the input utilDate is null
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date sqlDate = DateConvertor.convertUtilDateToSqlDate(utilDate);
        // Formattez la date
        return sdf.format(sqlDate);
    }
    public static String getAnnotation(java.util.Date utilDate){
        if (utilDate == null) {
            return null; // Return null if the input utilDate is null
        }
        // Formattez la date
        return DateConvertor.convertUtilDateToSqlDate(utilDate).toString();
    }
}
