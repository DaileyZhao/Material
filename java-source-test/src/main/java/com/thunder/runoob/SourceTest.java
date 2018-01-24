package com.thunder.runoob;

import java.util.HashMap;
import java.util.Map;

public class SourceTest {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new HashMap<>();
        map.put(111, "111");
        map.put(222, "222");
        MyThread thread=new MyThread();
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
        System.out.println("is Stop?="+thread.interrupted());
    }
    public static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=0;i<5000;i++){
                System.out.println("i="+i);
            }
        }
    }
}
