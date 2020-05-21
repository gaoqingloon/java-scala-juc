package com.lolo.lesson_10.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 第一个servlet
 *  步骤：
 *      继承HttpServlet 这个类
 *      重写请求方法
 *      配置在web.xml 中
 */
public class HelloServlet extends HttpServlet {

    /**
     * 接收get请求
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决中文乱码问题
        resp.setCharacterEncoding("utf-8");// 内容编码，防止出现中文乱码
        resp.setContentType("text/html;charset=utf-8");		//向浏览器输出内容

        //resp.getWriter().write("我是动态资源，date: " + new Date());
        resp.getWriter().write("This is HelloServlet");

    }
}
