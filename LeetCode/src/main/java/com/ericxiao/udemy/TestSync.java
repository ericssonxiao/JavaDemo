package com.ericxiao.udemy;

import java.util.logging.Logger;

/**
 * 考察知识点：
 * synchronize实例锁。
 * 并发下的内存可见性。
 *
 * java 都是从main方法执行的，上面说了有2个线程，但是这里就算修改线程优先级也没用，优先级是在2个程序都还没有执行的时候才有先后，
 * 现在这个代码一执行，主线程main已经执行了。对于属性变量 int b =100由于使用了synchronized也不会存在可见性问题（也没有必要再使用volatile申明），
 * 当执行1步骤的时候（Thread t = new Thread(tt);  //1）线程是new状态，还没有开始工作。当执行2步骤的时候（t.start(); //2）当调用start方法，
 * 这个线程才正真被启动，进入runnable状态，runnable状态表示可以执行，一切准备就绪了，但是并不表示一定在cpu上面执行，有没有真正执行取决服务cpu的调度。
 * 在这里当执行3步骤必定是先获得锁（由于start需要调用native方法，并且在用完成之后在一切准备就绪了，但是并不表示一定在cpu上面执行，
 * 有没有真正执行取决服务cpu的调度，之后才会调用run方法，执行m1方法）。这里其实2个synchronized方法里面的Thread.sheep其实要不要是无所谓的，
 * 估计是就为混淆增加难度。3步骤执行的时候其实很快子线程也准备好了，但是由于synchronized的存在，并且是作用同一对象，所以子线程就只有必须等待了。
 * 由于main方法里面执行顺序是顺序执行的，所以必须是步骤3执行完成之后才可以到4步骤，而由于3步骤执行完成，子线程就可以执行m1了。
 * 这里就存在一个多线程谁先获取到的问题，如果4步骤先获取那么main thread b=2000，
 * 如果子线程m1获取到可能就b已经赋值成1000或者还没有来得及赋值4步骤就输出了可能结果就是main thread b=1000或者main thread b=2000，
 * 在这里如果把6步骤去掉那么b=执行在前和main thread b=在前就不确定了。但是由于6步骤存在，所以不管怎么都是main thread b=在前面，
 * 那么等于1000还是2000看情况，之后b=1000是一定固定的了。
 *
 * https://juejin.im/post/59f9804851882554b836dd8b
 */
public class TestSync implements Runnable{

    final static Logger log = Logger.getGlobal();

    int b = 100;

    synchronized void m1() throws InterruptedException{
        b = 1000;
        Thread.sleep(5000);
        log.info("b = " + b);
    }

    synchronized void m2() throws InterruptedException{
        Thread.sleep(250);
        b = 2000;
    }

    public static void main(String args[]) throws InterruptedException{
        TestSync ts = new TestSync();
        Thread t = new Thread(ts); //1
        t.start(); //2
        ts.m2(); //3
        log.info("main thread b = " + ts.b); //4
    }

    @Override
    public void run() {
        try{
            m1();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
