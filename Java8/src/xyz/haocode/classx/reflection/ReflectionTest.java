package xyz.haocode.classx.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 *
 * @author LiuHao
 * @date 2020/4/13 11:01
 * @description 反射的类和方法 学习
*/
public class ReflectionTest {

    /**
     * 打印指定类的所有构造器信息
     * @param cl
     */
    public static void printConstructors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();

        for(Constructor constructor : constructors){
            String name = constructor.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length() > 0){
                System.out.print(name+"(");
            }

            Class[] paramTypes = constructor.getParameterTypes();
            for(int i=0;i<paramTypes.length;i++){
                if(i > 0){
                    System.out.print(",");
                }
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印指定类的所有方法
     * @param cl
     */
    public static void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();
        for(Method method:methods){
            Class retType = method.getReturnType();
            String name = method.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(method.getModifiers());
            if(modifiers.length()>0){
                System.out.print(modifiers+" ");
            }
            System.out.print(retType.getName()+" "+name+"(");
            Class[] paramTypes = method.getParameterTypes();
            for(int j=0;j<paramTypes.length;j++){
                if(j>0){
                    System.out.print(",");
                }
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印指定类型的所有域对象
     * @param cl
     */
    public static void printFields(Class cl){
        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            Class type = field.getType();
            String name = field.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(field.getModifiers());
            if(modifiers.length() > 0){
                System.out.print(modifiers+" ");
            }
            System.out.println(type.getName()+" "+name+";");
        }
    }

    public static void main(String[] args) {
        String name ;
        if(args.length >0){
            name = args[0];
        }else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter class name(e.g. java.util.Date):");
            name = scanner.next();
        }
        try{
            Class cl = Class.forName(name);
            Class supercl  = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length()>0){
                System.out.print(modifiers+" ");
            }
            System.out.print("class "+name);
            if(supercl !=null && supercl != Object.class){
                System.out.print("extends"+supercl.getName());
            }
            System.out.println("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            printFields(cl);
            System.out.println("}");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.exit(0);

    }


}
