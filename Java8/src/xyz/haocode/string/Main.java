package xyz.haocode.string;

import java.sql.SQLOutput;

/**
 *
 * @author LiuHao
 * @date 2020/4/7 22:46
 * @description string 主函数
*/
public class Main {

    public static void main(String[] args) {

        String string = new String("123");

        //子串 substring
        String str = "hello";
        System.out.println(str.substring(1));
        System.out.println(str.substring(2,3));

        //拼接 join
        String str1 = String.join("/","1","2","3");
        System.out.println(str1);

        //相等
        String str2 = "Hello";
        String str3 = "Hel"+"l"+"o";
        String str4 = "Hello1".substring(0,5);

        System.out.println(str2.hashCode());
        System.out.println(str3.hashCode());
        System.out.println(str4.hashCode());
        System.out.println("Hello1".substring(0,5).hashCode());

        System.out.println(str2==str3);
        System.out.println(str2==str4);

        System.out.println(str2.equals(str4));

        //码点和代码单元
        String str5 = "nihao";
        int cpCount = str5.codePointCount(0,str5.length());
        System.out.println(cpCount);

        int cpAt = str5.codePointAt(1);
        System.out.println(cpAt);
    }
}
