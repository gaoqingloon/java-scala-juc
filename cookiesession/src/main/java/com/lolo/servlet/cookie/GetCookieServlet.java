package com.lolo.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取cookie
 * 客户端通过请求头的方式将cookie信息发送给服务器端
 */
@WebServlet("/GetCookieServlet")
public class GetCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果获取所有的cookie信息
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "----" + cookie.getValue());
            }
        }
        else {
            System.out.println("没有cookie信息");
        }
        /*
_xsrf----2|1995ac93|5529fca9e5b1a1adb4b90b9d7c121540|1561104529
username-localhost-8888----2|1:0|10:1563363025|23:username-localhost-8888|44:OGMzMGNiM2QzNmY4NDFlNmI1YmQ1NmExMDVkNmFiN2M=|cbe73bd046905a01d87b9ca4397afb3368c33fd3dbfa69175c2605dc16a698d9
userName----123456
JSESSIONID----E6F7A631297C50F3D590846732751937
         */
    }
}
