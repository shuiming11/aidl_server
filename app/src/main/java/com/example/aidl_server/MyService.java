package com.example.aidl_server;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Carson_Ho on 16/11/8.
 */
public class MyService extends Service {
    private List<Book> boos = new ArrayList<>();

    // 实例化AIDL的Stub类(Binder的子类)
    MyAidl.Stub mBinder = new MyAidl.Stub() {

        //重写接口里定义的方法
        @Override
        public void test_Service() throws RemoteException {
            System.out.println("客户端通过AIDL与远程后台成功通信");
            System.out.println("当前进程名："+getProcessName(getApplicationContext()));
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            return boos;
        }

        @Override
        public Book addBook(Book book) throws RemoteException {
            book.setName("bookChnage");
            boos.add(book);
            Log.e("ADD BOOK","ADD BOOK "+book.getName());
            return book;
        }
    };

    public static String getProcessName(Context cxt) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("执行了onCreat()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("执行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("执行了onDestory()");
    }

    //在onBind()返回Stub类实例
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        System.out.println("执行了onBind()");

        return mBinder;
    }




    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("执行了onUnbind()");
        return super.onUnbind(intent);
    }

}
