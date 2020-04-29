package xyz.haocode.process;

/**
 *
 * @author LiuHao
 * @date 2020/4/9 14:35
 * @description 控制流程语句 主函数
*/
public class Main {
    public static void main(String[] args) {
        //带标签的break
        int a = 0;
        System.out.println(a);
        out:
        for(;;){
            a++;
            if(a==10){break out;}
        }
        System.out.println(a);

        //带标签的continue
        after:
        for(int i=0;i<10;i++){

            if(i==2){
                continue after;
            }
            System.out.println(i);
        }
    }
}
