package com.lolo.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建cookie案例
 * 服务器端创建cookie，通过响应头发送给客户端，存放在客户端
 */
@WebServlet("/AddCookieServlet")
public class AddCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // key value 自定义cookie
        Cookie cookie = new Cookie("userName", "123456");

        // 如果是负数，浏览器关闭就失效，如果是正数以秒为单位进行保存
        cookie.setMaxAge(60*60*24);  // cookie保存1天，没有任何公司可以保证一直保存

        // 将Cookie 发送到客户端
        resp.addCookie(cookie);

        System.out.println("创建cookie成功");
    }
}
