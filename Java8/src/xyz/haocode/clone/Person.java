package xyz.haocode.clone;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author LiuHao
 * @date 2020/4/14 9:32
 * @description person类
*/
public class Person implements Cloneable{

    private String name;
    private Date birthday;

    public Person(String name){
        this.name = name;
        this.birthday = new Date();
    }

    /**
     * 深度拷贝（将对象中可变域对象也会拷贝）
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Person clone()throws CloneNotSupportedException{
        Person cloned = (Person)super.clone();
        cloned.birthday = (Date) birthday.clone();
        return cloned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(int year,int month,int day) {
        Date newDate = new GregorianCalendar(year,month-1,day).getTime();
        birthday.setTime(newDate.getTime());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
