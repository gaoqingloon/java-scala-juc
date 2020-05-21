package com.lolo.lesson_10.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向 servlet
 * 手动实现，可以获取到参数
 */
@WebServlet("/RedirectServlet")
public class RedirectServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("username", "小龙");
        // 跳转有两种方式，转发，重定向
        //resp.sendRedirect(this.getServletContext().getContextPath() + "/GetDataServlet");
        resp.setStatus(302);
        resp.setHeader("Location", "HelloServlet2");
    }
}
