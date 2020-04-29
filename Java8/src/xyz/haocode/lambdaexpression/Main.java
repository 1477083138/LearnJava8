package xyz.haocode.lambdaexpression;

import xyz.haocode.clone.Person;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author LiuHao
 * @date 2020/4/14 10:43
 * @description lambda表达式学习
*/
public class Main {
    public static void main(String[] args) {
        //比较两个字符串长度大小
        Comparator<String> comparator = (first,second)->second.length()-first.length();

        String[] strings = {
          "sss",
          "xxxxx",
          "mxmxmmm",
          "x", "x",
                null
        };

        System.out.println(Arrays.toString(strings));

        //Arrays.sort(strings,comparator);

        System.out.println(Arrays.toString(strings));


        Predicate predicate = e -> e.equals(null);
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strings));
        System.out.println(list);
        //list.removeIf(predicate);
        System.out.println(list);


        repeat(10,i->System.out.println("sssss"+i));


        //使用Comparator.comparing，用lambda表达式实现排序逻辑
        List<Person> personList = list.stream().map(Person::new).collect(Collectors.toList());

        Person[] people = personList.stream().toArray(Person[]::new);

        System.out.println(Arrays.toString(people));

        Arrays.sort(people,Comparator.comparing(Person::getName,Comparator.nullsFirst(Comparator.naturalOrder())).thenComparing(Person::getBirthday));

        System.out.println(Arrays.toString(people));
    }

    public static void repeat(int n , IntConsumer action){
        for(int i=0;i<n;i++){
            action.accept(i);
        }
    }
}

/**
 * 定义一个函数式接口 接收lambda表达式
 */
@FunctionalInterface
interface IntConsumer{
    void accept(int value);
}
