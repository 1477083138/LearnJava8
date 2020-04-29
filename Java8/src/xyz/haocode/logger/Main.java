package xyz.haocode.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LiuHao
 * @date 2020/4/15 10:26
 * @description javaAPI自带的日志
*/
public class Main {

    public static void main(String[] args) {

        Logger.getGlobal().setLevel(Level.INFO);
        //Logger
        Logger.getGlobal().info("this is an INFO level log");

        Logger.getGlobal().config("this is a CONFIG level log");

        Logger logger = Logger.getLogger("xyz.haocode");

    }
}
