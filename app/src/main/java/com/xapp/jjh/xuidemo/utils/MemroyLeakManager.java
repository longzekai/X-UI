package com.xapp.jjh.xuidemo.utils;

import android.app.Application;
import android.os.Build;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Taurus on 2015/12/4.
 * 内存泄露监测管理器
 */
public class MemroyLeakManager {
    private static MemroyLeakManager instance = new MemroyLeakManager();
    private RefWatcher refWatcher;
    private MemroyLeakManager(){}
    public static MemroyLeakManager getInstance(){
        return instance;
    }

    /**
     * 初始化内存泄露监测模块
     */
    public void initLeakCanary(State state,Application application){
        //Not Contain Android M
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return;
        }
        if(state == State.DEBUG){
            refWatcher = LeakCanary.install(application);
        }
    }

    public void watchLeak(Object object){
        watch(object);
    }

    private void watch(Object object){
        if(refWatcher!=null)
            refWatcher.watch(object);
    }

    public RefWatcher getRefWatcher() {
        return refWatcher;
    }

}
