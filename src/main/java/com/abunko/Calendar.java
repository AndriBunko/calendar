package com.abunko;

public class Calendar {
    public static void main(String[] args) {
        if(args.length != 0) CalendarUtils.showCalendar( Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        else CalendarUtils.showDefaultCalendar();
    }
}
