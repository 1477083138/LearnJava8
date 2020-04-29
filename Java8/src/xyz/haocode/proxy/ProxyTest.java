package xyz.haocode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author LiuHao
 * @date 2020/4/14 17:36
 * @description JDK PROXY 动态代理 学习
*/
public class ProxyTest {

    public static void main(String[] args) {
//        Object[] element = new Object[1000];
//        for(int i=0;i<element.length;i++){
//            Integer value = i +1;
//            InvocationHandler handler = new TraceHandler(value);
//            Object proxy = Proxy.newProxyInstance(null,new Class[]{Comparable.class},handler);
//            element[i] = proxy;
//        }
//
//        Integer key = new Random().nextInt(element.length) +1;
//
//        int result = Arrays.binarySearch(element,key);
//
//        if(result >= 0){
//            System.out.println();
//            System.out.println(element[result]);
//        }
        Customer target = new Worker();
        InvocationHandler handler = new CustomerHandler(target);
        Customer proxy = (Customer) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);
        proxy.buy();
        System.out.println();
        proxy.sell();

        System.out.println(proxy.getClass());
    }

}

/**
 * 自定义调用处理器
 */
class TraceHandler implements InvocationHandler{
    private Object target;

    public TraceHandler(Object t){
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("."+ method.getName()+"(");
        if(args != null){
            for (int i=0; i<args.length; i++){
                System.out.print(args[i]);
                if(i < args.length -1){
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
        return method.invoke(target,args);
    }
}

interface Customer{
    void buy();
    void sell();
}

class Worker implements  Customer{

    @Override
    public void buy() {
        System.out.println("我tm正在买东西");
    }

    @Override
    public void sell() {
        System.out.println("我正在卖东西！！！");
    }


}

class CustomerHandler implements InvocationHandler{

    private Object target;

    public CustomerHandler(Object target){
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买东西前检查钱包有没有钱");
        Object inv = method.invoke(target,args);
        System.out.println("买完东西查询是否购买成功");
        return inv;
    }
}
