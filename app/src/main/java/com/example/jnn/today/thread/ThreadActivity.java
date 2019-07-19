package com.example.jnn.today.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jnn.today.R;

/**
 * 实现多线程——基础使用
 * 1）继承Thread（正常类 & 匿名内部类 & 卖票案例）
 * 2）实现Runnable接口
 */
public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }


    /**
     * 方式三：实现Runnable接口，辅助创建线程
     */
    class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }

    private void method() {
        // 步骤3：创建线程辅助对象，即 实例化 线程辅助类
        MyRunnable myRunnable = new MyRunnable();
        // 步骤4：创建线程对象，即 实例化线程类；线程类 = Thread类；
        // 创建时通过Thread类的构造函数传入线程辅助类对象
        // 原因：Runnable接口并没有任何对线程的支持，我们必须创建线程类（Thread类）的实例，从Thread类的一个实例内部运行
        Thread thread = new Thread(myRunnable);
        thread.start();
    }


    /**
     * 方式一： 继承Thread创建线程
     * 1，创建线程类
     * 2，复写run方法
     * 3，创建线程对象
     */
    class MyThread extends Thread {
        String name;
        int ticket = 100;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (ticket > 0) {
                ticket--;
                Log.e("Thread", name + "==>卖出了1张票，还剩" + ticket);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 方式二：Thread匿名内部类 创建线程
     */
    private void method2() {
        new Thread("匿名线程") {
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }

    /**
     * 卖票案例
     */
    public void btnStart(View view) {
        MyThread thread = new MyThread("售票员1");
        MyThread thread2 = new MyThread("售票员2");

        thread.start();
        thread2.start();
    }
}
