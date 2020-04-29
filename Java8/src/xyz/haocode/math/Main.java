package xyz.haocode.math;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author LiuHao
 * @date 2020/4/7 17:57
 * @description math 主函数
*/
public class Main {
    public static void main(String[] args) {
        //取一个0~8之间的数
        int num = (1+20)%8;

        int num1 = ((1-20)%8+8)%8;

        System.out.println(num);
        System.out.println(num1);

        //使用Math.floorMod,实现取一个0~8之间的数
        int num2 = Math.floorMod(1-20,8);
        System.out.println(num2);

        //位运算
        //与
        int num3 = (9 & 0b1000) / 0b1000;
        //或
        int num4 = 9 | 0b1010;
        //异或
        int num5 = 9 ^ 0b1011;
        //not
        int num6 = ~0b1000;

        // 位模式左移 <<
        int num7 = 1 << 31;
        // 位模式右移 >>
        int num8 = 1 >> 31;

        //>>> 会用符号位填充高位
        //表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
        int num9 = -8 >>> 1;



        System.out.println(num3);

        System.out.println(num4);

        System.out.println(num5);

        System.out.println(num6);

        System.out.println(num7);

        System.out.println(num8);

        System.out.println(num9);

        //超大数
        BigInteger bi1 = new BigInteger("100000000000000000000000000000000000000000000000000000000000000");
        BigInteger bi2 = BigInteger.valueOf(1234567);
        BigInteger bi3 = bi1.add(bi2);
        System.out.println(bi3);
        BigDecimal bd1 = new BigDecimal("10000000000000000000000000000000000000000000000000000000000000000.00");
        BigDecimal bd2 = BigDecimal.valueOf(10000);
        BigDecimal bd3 = bd1.add(bd2);
        System.out.println(bd3);



    }
}
