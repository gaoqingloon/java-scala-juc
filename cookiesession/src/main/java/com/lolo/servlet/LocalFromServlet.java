package com.lolo.servlet;

import com.lolo.servlet.session.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 跳转到 from.jsp
 */
@WebServlet("/LocalFromServlet")
public class LocalFromServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 生成token
        String tokenValue = TokenUtils.getToken();
        HttpSession session = req.getSession();
        session.setAttribute("sessionToken", tokenValue);
        req.getRequestDispatcher("from.jsp").forward(req, resp);
    }
}
