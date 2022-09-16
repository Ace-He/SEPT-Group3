package com.Group3.common.domain;

public class RequestDetailThreadLocal {

    private static ThreadLocal<RequestDetail> threadLocal = new ThreadLocal<>();

    /*
     * 设置请求信息到当前线程中
     */
    public static void setRequestDetail(RequestDetail requestDetail){
        threadLocal.set(requestDetail);
    }

    /**
     * 从当前线程中获取请求信息
     */
    public static RequestDetail getRequestDetail(){return threadLocal.get();}

    /**
     * 销毁
     */
    public static void remove(){
        threadLocal.remove();
    }
}
