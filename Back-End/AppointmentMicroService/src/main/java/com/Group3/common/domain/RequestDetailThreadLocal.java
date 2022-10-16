package com.Group3.common.domain;

public class RequestDetailThreadLocal {

    private static ThreadLocal<RequestDetail> threadLocal = new ThreadLocal<>();

    /*
     * Sets request information to the current thread
     */
    public static void setRequestDetail(RequestDetail requestDetail){
        threadLocal.set(requestDetail);
    }

    /**
     * Retrieves request information from the current thread
     */
    public static RequestDetail getRequestDetail(){return threadLocal.get();}

    /**
     * Remove
     */
    public static void remove(){
        threadLocal.remove();
    }
}
