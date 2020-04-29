package xyz.haocode.collection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LiuHao
 * @date 2020/4/12 10:12
 * @description 集合类 主方法
*/
public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        ArrayList<String> list1 = new ArrayList<>(100);
        list1.ensureCapacity(100);
        System.out.println(list1.size());
        list1.trimToSize();

    }
}
