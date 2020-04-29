package xyz.haocode.innerclass;

/**
 *
 * @author LiuHao
 * @date 2020/4/14 16:34
 * @description
*/
public class Main {
    public static void main(String[] args) {
        Object object = new Object();
        object.getClass().getEnclosingClass();
    }
}
