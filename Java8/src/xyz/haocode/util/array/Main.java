package xyz.haocode.util.array;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author LiuHao
 * @date 2020/4/9 15:34
 * @description
*/
public class Main {

    public static void main(String[] args) {

       /* //使用Arrays.sort对数组进行排序，sort方法使用的快排
        int[] arr = {1,2,3,4,5,3,2,4,5,-1};
        //Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr,0,arr.length);
        System.out.println(Arrays.toString(arr));

        //拷贝返回一个与原数组相同的数组
        int[] arr1 = Arrays.copyOf(arr,arr.length);
        System.out.println(Arrays.toString(arr1));

        //二分法查找元素
        int index = Arrays.binarySearch(arr,-1);
        System.out.println(index);

        //两个数组是否完全相等，内容和下标也要相等
        boolean isEquals = Arrays.equals(arr,arr1);
        System.out.println(isEquals);*/

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        final int day = 1;
        int hour = 0;
        int minute = 0;
        int second = 0;
        Calendar currentMonthStartDate = Calendar.getInstance();
        currentMonthStartDate.set(year,month,day,hour,minute,second);
        Date date = currentMonthStartDate.getTime();
        System.out.println(format.format(date));

        Calendar lastMonthStartDate = Calendar.getInstance();
        lastMonthStartDate.set(year,month-1,day,hour,minute,second);
        Date lastDate = lastMonthStartDate.getTime();
        System.out.println(format.format(lastDate));
    }
}
