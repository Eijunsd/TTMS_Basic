package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        SimpleDateFormat str2date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date();
        date.toString();
        System.out.println(str2date.format(date));
    }
}
