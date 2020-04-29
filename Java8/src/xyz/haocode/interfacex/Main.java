package xyz.haocode.interfacex;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author LiuHao
 * @date 2020/4/13 17:01
 * @description 接口相关学习
*/
public class Main {

    public static void main(String[] args) {
        Person person = new Person(11);
        Person person1 = new Person(22);
        Person person2 = new Person(33);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person);
        list.add(person2);


        Object[] people = list.toArray();
        System.out.println(Arrays.toString(people));

        Arrays.sort(people);

        System.out.println(Arrays.toString(people));


        Student student = new Student(11);
        System.out.println(student.getName());

    }

}
class Person implements Comparable<Person>,Cloneable{
    public Person(int age){
        this.age = age;
    }
    private int age;
    private String name;




    @Override
    public int compareTo(Person other) {
        return Integer.compare(age,other.age);
    }


    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

interface Named{
    default String getName(){
        return getClass().getName()+hashCode();
    }
}

class Student extends Person implements Named{

    public Student(int age){
        super(age);
    }


}


