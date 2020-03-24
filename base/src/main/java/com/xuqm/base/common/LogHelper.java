package com.xuqm.base.common;

import com.socks.library.KLog;

import java.util.Locale;

/**
 * 日志库用的是 XLog(https://github.com/elvishew/xLog)
 * 平常自己调试时候不想用那么多，就随便写了个类
 */
public class LogHelper {


    public static void d(String tag, Object object) {
        KLog.d(tag, object);
    }

    public static void d(Object object) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        KLog.d(tag, object);
    }

    public static void e(String tag, Object object) {
        KLog.e(tag, object);
    }

    public static void e(Object object) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        KLog.e(tag, object);
    }

    public static void e(String msg, Throwable tr) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        KLog.e(tag, msg, tr);
    }

    public static void e(String tag, String msg, Throwable tr) {
        KLog.e(tag, msg, tr);
    }


    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        return String.format(Locale.getDefault(), tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
