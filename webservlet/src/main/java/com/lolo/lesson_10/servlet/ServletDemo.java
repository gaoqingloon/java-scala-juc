package com.lolo.lesson_10.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 的生命周期
 *
 * servlet 初始化次数？
 *      多个请求同时访问 servlet 会被实例化多少次？ 1次
 *          如何证明: 通过构造函数 被执行的次数 只执行1次，单例的，
 *          反射 classForName 走无参构造，只执行1次，单例的
 *
 * init()       只会执行一次
 * service()    请求发送 -- get/post/put/delete
 * destroy()    只执行一次
 */
@WebServlet("/ServletDemo")
public class ServletDemo extends HttpServlet {

    public ServletDemo() {
        System.out.println("ServletDemo 构造函数..." + ServletDemo.class);
    }

    /**
     * 初始化
     */
    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    /**
     * doGet 是被 service执行
     * 处理请求
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
