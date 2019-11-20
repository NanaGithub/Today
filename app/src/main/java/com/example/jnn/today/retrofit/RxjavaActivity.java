package com.example.jnn.today.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jnn.today.R;
import com.jnn.mylibrary.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

/**
 * RxJava
 * 把被观察者比喻作 网络请求数据，观察者是 Activity/View。
 * view订阅网络请求数据「实际写法是倒过来的」。
 * 当View需要更新试图时，网络请求到数据 就会返回给View，View观察到数据变化就更新试图。
 */
public class RxjavaActivity extends AppCompatActivity {
    public static final String TAG = RxjavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        //观察者
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                if ("sdsd".equals(s)) {

                }
                Log.e(TAG, "onNext======" + s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtil.e(TAG, "onComplete");
            }
        };
        //被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //被观察者有一肚子话想说,于是发给观察者
                //由被观察者向观察者的事件传递，即观察者模式。
                emitter.onNext("你是谁");
                emitter.onNext(null);
            }
        });
        //订阅,方法真正开始执行
        observable.onErrorReturnItem("这是发生错误时的默认值")
                .subscribe(observer);
    }

    /**
     * 【变换 map】
     * 所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。
     * map：一对一转换
     * flatMap：一对多
     * lift：
     * 其中很多操作符类似于数学公式
     */
    private void map() {
        //window(count),将数据分为count组数据，相当于执行 onNext——>onComplete count次。
        Observable.just(1, 2, 3, 5, 3)
                .window(2)
                .subscribe(new Observer<Observable<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Observable<Integer> integerObservable) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable
                .just(3.1415926)
                //参数类型 String，变换后类型Bitmap
                .map(new Function<Double, Integer>() {
                    @Override
                    public Integer apply(Double s) throws Exception {//参数类型
                        //变换操作在这里进行
                        //返回类型
                        String value = String.valueOf(s);
                        Integer integer = Integer.parseInt(value);
                        return integer;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer o) {
                        Log.e("TAG", "变换后" + o + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 【线程调度器 Scheduler】
     */
    private void schedulers() {
        Observable.just("我", "你", "他")
                //指定subscribe()发生在 IO线程，叫做事件消费线程。
                //被创建的事件的内容 "我", "你", true 将会在 IO 线程发出
                .observeOn(Schedulers.io())
                // 指定subscribe()所发生的线程在 Android主线程，叫做事件消费线程。
                //那么打印数据将会发生在主线程中
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        //回调
                        Log.e("TAG", value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 【事件序列】用来创建一个被观察者
     * create、just、from
     */
    private void observable() {
        //create() 方法是 RxJava 最基本的创造事件序列的方法。基于这个方法， RxJava 还提供了一些方法用来快捷创建事件队列

        //just， 将传入的参数依次发送出来。
        Observable.just("贾小呆", 18, true);

        //fromArray，将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
        int arry[] = new int[]{1, 3, 4, 6};
        Observable.fromArray(arry);
        //fromCallable
        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return " Callable 是 java.util.concurrent 中的 Callable，" +
                        "Callable 和 Runnable 的用法基本一致，只是它会返回一个结果值，这个结果值就是发给观察者的。";
            }
        });
        //fromIterable，直接发送一个集合给观察者，观察者接收时 是逐条打印的
        List<String> list = new ArrayList<>();
        list.add("1");
        Observable.fromIterable(list);
    }

    public void behaviorSubject(View view) {
        final Observer<Object> observer = new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtil.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                LogUtil.e(TAG, o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                LogUtil.e(TAG, "onComplete");
            }
        };

        //用 BehaviorSubject 创建一个对象
        BehaviorSubject<String> subject2 = BehaviorSubject.createDefault("初始化");
        subject2.subscribe();
        subject2.onNext("订阅前，发送的第一条数据");
        subject2.onNext("订阅前，发送的第二条数据 距离订阅最近");
        subject2.subscribe(observer);
        observer.onNext("订阅后，发送的数据正常");
        observer.onNext("Observer会接收到 BehaviorSubject 被订阅之前的最后一个数据，如果订阅前没有发送数据，则会发送默认数据");
        observer.onComplete();

        //用 BehaviorSubject 创建一个观察者
        Observable<Object> observable = BehaviorSubject.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
            }
        });

    }

    public void asyncSubject(View view) {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("订阅前，发送的数据，不会被接收");
        asyncSubject.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtil.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                LogUtil.e(TAG, o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                LogUtil.e(TAG, "onComplete");
            }
        });
        asyncSubject.onNext("订阅后，发送的第一条数据");
        asyncSubject.onNext("订阅后，onComplete前，发送的数据");
        asyncSubject.onNext("Observer会接收到 AsyncSubject 被 onComplete()前的最后一个数据，如果没有 onComplete()，则不接收任何数据。");
        asyncSubject.onComplete();
    }

    public void publishSubject(View view) {
        /**
         * PublishSubject比较容易理解，相对比其他Subject常用，它的Observer只会接收到PublishSubject被订阅之后发送的数据。
         */
    }


}
