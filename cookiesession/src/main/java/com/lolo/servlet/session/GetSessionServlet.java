package com.lolo.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 获取Session信息
 */
@WebServlet("/GetSessionServlet")
public class GetSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 默认是true，表示如果没有session，就会创建一个session
        // 如果是false，若没有找到session，就不会创建一个session
        HttpSession session = req.getSession(false);
        if (session != null) {
            String userName = (String) session.getAttribute("userName");
            System.out.println("GetSessionServlet-- UserName: " + userName);
            System.out.println("sessionId: " + session.getId());
        }
        else {
            System.out.println("没有session信息");
        }
    }
}

