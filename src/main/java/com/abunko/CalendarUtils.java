package com.abunko;

import sun.util.resources.LocaleData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Locale;


public final class CalendarUtils {

    private static final Locale LOC = Locale.getDefault();
    private static final LocalDate NOW_DATE = LocalDate.now();
    private static int numOfFirstDay;
    private static int numDaysInMonth;

    public static void showDefaultCalendar() {

        printDayOfWeek();

        numOfFirstDay = NOW_DATE.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
        numDaysInMonth = NOW_DATE.lengthOfMonth();

        printNumberOfDaysDefault(numOfFirstDay, numDaysInMonth);
    }


    public static void showCalendar(int year, int month) {

        YearMonth date = YearMonth.of(year, month);

        if (date.getYear() == NOW_DATE.getYear() && date.getMonth() == NOW_DATE.getMonth()) {
            showDefaultCalendar();
        } else {
            printDayOfWeek();
            LocalDate localDate = date.atDay(1);
            DayOfWeek day = localDate.getDayOfWeek();

            numOfFirstDay = day.getValue();
            numDaysInMonth = localDate.lengthOfMonth();

            printNumberOfDays(numOfFirstDay, numDaysInMonth);
        }
    }


    private static void printDayOfWeek() {

        System.out.println(" Поточна дата \033[92m" + NOW_DATE + "\033[0m");

        Arrays.stream(DayOfWeek.values()).map(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SUNDAY) ||
                dayOfWeek.equals(DayOfWeek.SATURDAY) ? "\033[94m " + dayOfWeek.getDisplayName(TextStyle.SHORT, LOC) + "\033[0m" :
                " " + dayOfWeek.getDisplayName(TextStyle.SHORT, LOC)).forEach(System.out::print);

        System.out.println();
    }


    private static void printNumberOfDays(int numOfFirstDay, int numDaysInMonth) {
        int i;
        for (i = 1; i < numOfFirstDay; i++) System.out.print("   ");
        for (int j = 1; j <= numDaysInMonth; j++) {
            if ((i + j) % 7 == 0) {
                String s = Integer.toString(j).length() < 2 ? "\033[94m" + "  " + Integer.toString(j) + "\033[0m" : "\033[94m" + " " + Integer.toString(j) + "\033[0m";
                String s1 = Integer.toString(++j).length() < 2 ? "\033[94m" + "  " + Integer.toString(j) + "\033[0m" : "\033[94m" + " " + Integer.toString(j) + "\033[0m";
                System.out.printf(s);
                System.out.printf("%3s\n", s1);
            } else System.out.printf("%3s", j);
        }
    }

    private static void printNumberOfDaysDefault(int numOfFirstDay, int numDaysInMonth) {
        int i;
        String s;
        String s1;
        for (i = 1; i < numOfFirstDay; i++) System.out.print("   ");
        for (int j = 1; j <= numDaysInMonth; j++) {
            if ((i + j) % 7 == 0) {
                if (j == NOW_DATE.getDayOfMonth()) {
                    s = Integer.toString(j).length() < 2 ? "\033[92m" + "  " + Integer.toString(j) + "\033[0m" : "\033[92m" + " " + Integer.toString(j) + "\033[0m";
                    s1 = Integer.toString(++j).length() < 2 ? "\033[94m" + "  " + Integer.toString(j) + "\033[0m" : "\033[94m" + " " + Integer.toString(j) + "\033[0m";
                } else if (j + 1 == NOW_DATE.getDayOfMonth()) {
                    s = Integer.toString(j).length() < 2 ? "\033[94m" + "  " + Integer.toString(j) + "\033[0m" : "\033[94m" + " " + Integer.toString(j) + "\033[0m";
                    s1 = Integer.toString(++j).length() < 2 ? "\033[92m" + "  " + Integer.toString(j) + "\033[0m" : "\033[92m" + " " + Integer.toString(j) + "\033[0m";
                } else {
                    s = Integer.toString(j).length() < 2 ? "\033[94m" + "  " + Integer.toString(j) + "\033[0m" : "\033[94m" + " " + Integer.toString(j) + "\033[0m";
                    s1 = Integer.toString(++j).length() < 2 ? "\033[94m" + "  " + Integer.toString(j) + "\033[0m" : "\033[94m" + " " + Integer.toString(j) + "\033[0m";
                }

                System.out.printf(s);
                System.out.printf("%3s\n", s1);
            } else if (j == NOW_DATE.getDayOfMonth()) {
                System.out.print(Integer.toString(j).length() < 2 ? "\033[92m" + "  " + Integer.toString(j) + "\033[0m" : "\033[92m" + " " + Integer.toString(j) + "\033[0m");
            } else
                System.out.printf("%3s", j);

        }
    }
}



