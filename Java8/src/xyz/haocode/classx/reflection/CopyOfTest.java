package xyz.haocode.classx.reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author LiuHao
 * @date 2020/4/13 14:39
 * @description 使用反射编写泛型数组代码
*/
public class CopyOfTest {

    /**
     * 拷贝泛型数组（未使用反射）
     * @param a
     * @param newLength
     * @return
     */
    public static Object[] badCopyOf(Object[] a,int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newLength));
        return newArray;
    }


    /**
     * 拷贝泛型数组（使用反射）
     * @param a
     * @param newLength
     * @return
     */
    public static Object goodCopyOf(Object a,int newLength){
        Class cl = a.getClass();
        if(!cl.isArray()){
            return null;
        }
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType,newLength);
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3};
        a = (int[]) goodCopyOf(a,10);
        System.out.println(Arrays.toString(a));

        String[] b = {"tom","dick","harry"};
        b = (String[])goodCopyOf(b,10);
        System.out.println(Arrays.toString(b));

        //会产生异常
        b = (String[]) badCopyOf(b,10);

    }
}
