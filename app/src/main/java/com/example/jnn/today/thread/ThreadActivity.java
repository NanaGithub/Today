package com.example.jnn.today.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jnn.today.R;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现多线程——基础使用
 * 1）继承Thread
 * 2）实现Runnable接口
 * 3）Handler
 * 实现多线程——组合使用
 * 1）HandlerThread
 * 2）IntentService
 * 实现多线程——高级使用
 * 1）线程池
 */
public class ThreadActivity extends AppCompatActivity {
    /**
     * 应用已有线程池
     */
    private void methodThreadPool() {
        Executor threadPoolExecutor = new ThreadPoolExecutor(3, 5,
                1000, TimeUnit.MILLISECONDS,
                null);
        // 2. 向线程池提交任务：execute（）
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        ((ThreadPoolExecutor) threadPoolExecutor).shutdown();


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //执行线程池
            }
        });
        fixedThreadPool.shutdown();

    }


    /**
     * HandlerThread
     */
    private void methodHandlerThread() {
        HandlerThread handlerThread = new HandlerThread("一个工作线程");
        handlerThread.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    /**
     * Handler使用方式
     */
    //步骤1：主线程中，自定义Handler子类（继承Handler类） &复写handleMessage（）方法

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // 通过复写handlerMessage() 从而确定更新UI的操作
            super.handleMessage(msg);
        }
    }


    private void methodForHandler() {
        /**
         * 基本使用思路
         */
        // 步骤2：在主线程中创建Handler实例
        final MyHandler handler = new MyHandler();
        // 步骤3：创建所需的消息对象
        Message msg = Message.obtain();
        msg.what = 1;
        // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
        // 可通过sendMessage（） / post（）
        handler.sendMessage(msg);


        /**
         * 模拟handler使用场景，主线程与子线程通信媒介
         */
        //方式一「sendMessage」：步骤1：创建一个子线程
        new Thread() {
            @Override
            public void run() {
                //让子线程睡10秒，假设此时他正在请求数据
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //好了，请求完了，需要更新UI
                //可是子线程又不能更新UI，媒介Handler这时候发挥作用了
                //步骤2：更新UI
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "我是子线程，我请求到数据了，我要更新UI";
                handler.sendMessage(message);
            }
        }.start();

        //方式二「post」：该方法可直接处理，无需重写handleMessage
        new Thread() {
            @Override
            public void run() {
                //让子线程睡10秒，假设此时他正在请求数据
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //步骤2：更新UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI操作
                    }
                });
            }
        }.start();
    }


    /**
     * 方式三：实现Runnable接口，辅助创建线程
     */
    class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }

    private void methodForRunnable() {
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
    private void methodForThread() {
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
