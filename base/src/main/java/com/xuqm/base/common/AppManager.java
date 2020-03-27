package com.xuqm.base.common;

import android.app.Activity;

import java.util.Stack;

/**
 * activity的管理栈
 */
public class AppManager {


    public static AppManager getInstance() {
        return APPHolder.INSTANCE;
    }

    private static class APPHolder {
        private static AppManager INSTANCE = new AppManager();
    }

    private AppManager() {
        activityStack = new Stack<>();
    }

    private Stack<Activity> activityStack;

    //添加一个新的act
    public void pushActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 推出一个activity 其实toolbar的返回按钮，可以直接使用这个方法
     *
     * @param activity 需要退出的activity
     */
    public void popActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
            }

        }
    }

    /**
     * 获取当前最上面的那个activity
     *
     * @return
     */
    public Activity getActivity() {
        return activityStack.lastElement();
    }

    /**
     * 退出app
     */
    public void exit() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getActivity();
                if (activity == null) break;
                popActivity(activity);
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
