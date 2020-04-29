package xyz.haocode.classx;

import java.awt.*;
import java.net.URLClassLoader;

/**
 *
 * @author LiuHao
 * @date 2020/4/10 11:57
 * @description 类和对象
*/
public class Main {

    //方法参数
    public static void main(String[] args) {
        int x = 1;
        setX(x);
        System.out.println(x);

        Object obj = new Object();
        System.out.println(obj);

        Object obj1 = new Person();
        Person person = (Person) obj1;

        Person person1 = (Person)obj;

    }

    /**
     * 按值调用 call by value
     * 此时的参数值 是对传参值的拷贝
     * */
    private static void setX(double x){
        x = x * 3;
    }


}

class Person{
    private String name;

    private String getName(){
        return name;
    }
}
