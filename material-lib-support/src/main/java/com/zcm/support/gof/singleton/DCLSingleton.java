package com.zcm.support.gof.singleton;

/**
 * Created by zcm on 17-6-1.
 * 双重检测机制
 * 一次是检测instance 实例是否为空，进行第一次过滤，在同步块中进行第二次检测，
 * 因为当多个线程执行第一次检测通过后会同时进入同步块，
 * 那么此时就有必要进行第二次检测来避免生成多个实例。
 *
 * 在计算机语言中，初始化包含了三个步骤
 *   1,分配内存
 *   2,执行构造方法初始化
 *   3,将对象指向分配的内存空间
 *
 * 在java语言中有一个关键字volatile，我们都知道它提供了内存可见性这一特性，
 * 其实它还有一个作用就是防止指令重排序，
 * 那么我们把变量singleton用volatile修饰下就可以了。
 */

public class DCLSingleton {
    private static volatile DCLSingleton instance;
    private DCLSingleton(){

    }
    public static synchronized DCLSingleton getSingleton(){
        if (instance==null){
            synchronized (DCLSingleton.class){
                if (instance==null){
                    instance=new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
