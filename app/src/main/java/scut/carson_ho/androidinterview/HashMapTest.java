package scut.carson_ho.androidinterview;

import android.content.Intent;
import android.os.Looper;
import android.support.annotation.WorkerThread;

public class HashMapTest {

    @Override
    public void onCreate() {
        super.onCreate();

        // 1. 通过实例化andlerThread新建线程 & 启动；故 使用IntentService时，不需额外新建线程
        // HandlerThread继承自Thread，内部封装了 Looper
        HandlerThread thread = new HandlerThread("IntentService[" + mName + "]");
        thread.start();

        // 2. 获得工作线程的 Looper & 维护自己的工作队列
        mServiceLooper = thread.getLooper();

        // 3. 新建mServiceHandler & 绑定上述获得Looper
        // 新建的Handler 属于工作线程 ->>分析1
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }


    /**
     * 分析1：ServiceHandler源码分析
     **/
    private final class ServiceHandler extends Handler {

        // 构造函数
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        // IntentService的handleMessage（）把接收的消息交给onHandleIntent()处理
        @Override
        public void handleMessage(Message msg) {

            // onHandleIntent 方法在工作线程中执行
            // onHandleIntent() = 抽象方法，使用时需重写 ->>分析2
            onHandleIntent((Intent)msg.obj);
            // 执行完调用 stopSelf() 结束服务
            stopSelf(msg.arg1);

        }
    }

    /**
     * 分析2： onHandleIntent()源码分析
     * onHandleIntent() = 抽象方法，使用时需重写
     **/
    @WorkerThread
    protected abstract void onHandleIntent(Intent intent);

}
