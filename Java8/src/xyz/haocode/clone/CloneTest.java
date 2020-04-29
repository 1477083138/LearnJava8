package xyz.haocode.clone;


/**
 *
 * @author LiuHao
 * @date 2020/4/14 9:31
 * @description 克隆 深拷贝实现
*/
public class CloneTest {

    public static void main(String[] args) {
        try{
            Person person = new Person("jack");
            person.setBirthday(1999,4,4);

            Person copy = person.clone();
            copy.setName("john");
            copy.setBirthday(2000,1,1);

            System.out.println(person);
            System.out.println(copy);
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
