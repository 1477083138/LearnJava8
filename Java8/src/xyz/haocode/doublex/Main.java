package xyz.haocode.doublex;

/**
 *
 * @author LiuHao
 * @date 2020/4/7 17:24
 * @description 主函数
*/
public class Main {
    public static void main(String[] args) {

        //测试int精度
        System.out.println(2-1);


        //测试double精度.由于浮点数值使用二进制系统表示，无法准确的表示1/10的数值
        //result 0.8999999999999999
        System.out.println(2.0-1.1);
        //result 0.8999999999999999
        System.out.println(2.00-1.10);




    }
}
