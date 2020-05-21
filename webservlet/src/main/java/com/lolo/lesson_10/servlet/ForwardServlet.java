package com.lolo.lesson_10.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 转发请求 servlet
 */
@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("username", "小龙");
        // 跳转有两种方式，转发，重定向
        req.getRequestDispatcher("/GetDataServlet").forward(req, resp);
    }
}
