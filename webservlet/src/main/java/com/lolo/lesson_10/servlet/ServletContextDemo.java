package com.lolo.lesson_10.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletContextDemo")
public class ServletContextDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 需要跳转到 HelloServlet2 用什么方式？ 重定向或者转发

        // 获取当前项目路径
        ServletContext servletContext = this.getServletContext();
        String contextPath = servletContext.getContextPath();

        resp.sendRedirect(contextPath + "/HelloServlet2");
    }
}
