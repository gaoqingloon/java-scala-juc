package com.lolo.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 创建Session信息
 */
@WebServlet("/CreateSessionServlet")
public class CreateSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 默认是true，表示如果没有session，就会创建一个session
        // 如果是false，若没有找到session，就不会创建一个session
        HttpSession session = req.getSession();
        session.setAttribute("userName", "小龙");
        System.out.println("sessionId: " + session.getId());

        System.out.println("保存session成功！");
    }
}
