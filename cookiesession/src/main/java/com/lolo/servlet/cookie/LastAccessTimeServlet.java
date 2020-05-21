package com.lolo.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 显示最后一次访问时间
 */
@WebServlet("/LastAccessTimeServlet")
public class LastAccessTimeServlet extends HttpServlet {

    // cookie的key值
    private static final String COOKIE_KEY_LASTACCESSTIME = "COOKIE_KEY_LASTACCESSTIME";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");// 防止浏览器显示乱码
        // 1. 获取cookie信息
        String lastAccessTime = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(COOKIE_KEY_LASTACCESSTIME)) {
                    lastAccessTime = cookie.getValue();
                    break;
                }
            }
        }

        // 2. 如果cookie信息没有数据，说明第一次访问，有数据获取上一次的cookie的值
        if (lastAccessTime == null) {
            resp.getWriter().write("你是首次访问");
        }
        else {
            resp.getWriter().write("你上次的访问时间是: " + lastAccessTime);
        }

        // 3. 现在访问的这个登陆时间存放在cookie里面
        // 当前的这个日期存放在cookie中
        String currentTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
        Cookie cookie = new Cookie(COOKIE_KEY_LASTACCESSTIME, currentTime);
        cookie.setMaxAge(60*60*24);
        resp.addCookie(cookie);
    }
}
