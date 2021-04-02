package com.abo.enlistment;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("App running...");

        String str = "1800";
        LocalTime parsedTime = LocalTime.parse(str, DateTimeFormatter.ofPattern("HHmm"));
        System.out.println(parsedTime);
        System.out.println((parsedTime.getHour() >= 8 && parsedTime.getHour() <= 17) ? true : false);
        System.out.println((parsedTime.getMinute() == 30 || parsedTime.getMinute() == 00) ? true : false);
    }
}
