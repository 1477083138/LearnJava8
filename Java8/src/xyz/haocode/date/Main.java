package xyz.haocode.date;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author LiuHao
 * @date 2020/4/10 0:06
 * @description
*/
public class Main {
    public static void main(String[] args) {
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate.toString());
//        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
//        System.out.println(dayOfWeek);
//        System.out.println(localDate.minusDays(1));
//
//
//        NumberFormat currentFormatter = NumberFormat.getCurrencyInstance();
//        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
//
//        double x = 0.1;
//
//        System.out.println(currentFormatter.format(x));
//        System.out.println(percentFormatter.format(x));
        final int month = 2;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentMonthStartDate = getCurrentMonthStartDate(month);
        System.out.println(format.format(currentMonthStartDate));

        Date lastMonthStartDate = getLastMonthStartDate(month);
        System.out.println(format.format(lastMonthStartDate));

        Date lastMonthEndDate = getLastMonthEndDate(month);
        System.out.println(format.format(lastMonthEndDate));

        Date theDay = getLastMonthTheDayDate(2020,2,1);
        System.out.println(format.format(theDay));
    }

    private static Date getCurrentMonthStartDate(int month){
        Calendar now = Calendar.getInstance();
//        final int year = now.get(Calendar.YEAR);
        final int year = 2001;
        //final int month = now.get(Calendar.MONTH);
        final int day = 1;
        final int hour = 0;
        final int minute = 0;
        final int second = 0;
        Calendar currentMonthStartDate = Calendar.getInstance();
        currentMonthStartDate.set(year,month,day,hour,minute,second);
        return currentMonthStartDate.getTime();
    }

    private static Date getLastMonthStartDate(int month){
        Calendar lastMonthStartDate = Calendar.getInstance();
        lastMonthStartDate.setTime(getCurrentMonthStartDate(month));
        lastMonthStartDate.add(Calendar.MONTH,-1);
        return lastMonthStartDate.getTime();
    }

    private static Date getLastMonthEndDate(int month){
        Calendar lastMonthEndDate = Calendar.getInstance();
        lastMonthEndDate.setTime(getLastMonthStartDate(month));
        final int dayOfMonth = lastMonthEndDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        final int hourOfDay = lastMonthEndDate.getMaximum(Calendar.HOUR_OF_DAY);
        final int minute = lastMonthEndDate.getMaximum(Calendar.MINUTE);
        final int second = lastMonthEndDate.getMaximum(Calendar.SECOND);
        lastMonthEndDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        lastMonthEndDate.set(Calendar.HOUR_OF_DAY,hourOfDay);
        lastMonthEndDate.set(Calendar.MINUTE,minute);
        lastMonthEndDate.set(Calendar.SECOND,second);
        return lastMonthEndDate.getTime();
    }

    private static Date getLastMonthTheDayDate(int year,int month,int day){
        Calendar now = Calendar.getInstance();
        now.set(year,month,day);
        now.add(Calendar.DAY_OF_MONTH,-30);
        return now.getTime();
    }
}
