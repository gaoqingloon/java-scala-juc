package com.lolo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/DoFormServlet")
public class DoFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        if (!isSubmit(req)) {
            System.out.println("您已经提交了数据，或token错误");
            resp.getWriter().write("您已经提交了数据，或token错误");
            return;
        }

        String userName = req.getParameter("userName");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据库插入数据, UserName: " + userName);
        // 插入数据库
        resp.getWriter().write("保存成功！");
        req.getSession().getAttribute("sessionToken");
    }

    public Boolean isSubmit(HttpServletRequest request) {

        String parameterToken = request.getParameter("parameterToken");
        String sessionToken = (String) request.getSession().getAttribute("sessionToken");
        // 判断是否提交
        if (sessionToken == null) {
            return false;
        }
        // 判断伪造Token
        if (!(parameterToken.equals(sessionToken))) {
            return false;
        }
        return true;
    }
}
