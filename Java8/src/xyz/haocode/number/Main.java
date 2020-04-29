package xyz.haocode.number;

/**
 *
 * @author LiuHao
 * @date 2020/4/12 10:40
 * @description 包装器 主方法
*/
public class Main {
    public static void main(String... args) {
        Integer n = 3;
        int m = 1;
        int result = n++ + m;
        System.out.println(n);
        System.out.println(result);


        Integer w = 1;
        Double x = 2.0;
        Double y = w+x;
        System.out.println(y);
        
    }
}
