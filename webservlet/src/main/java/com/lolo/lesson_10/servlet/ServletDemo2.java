package com.lolo.lesson_10.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 在如何证明servlet 线程是否安全？
 * 如果servlet是单例的？
 *      servlet 不安全
 * 如果servlet是多例的？
 *      servlet 安全
 *
 * 当多个线程共享一个全局变量，可能会受到其他线程的干扰，数据会导致错乱，这就是线程不安全
 *
 * 1. 证明servlet是单例的
 *      看构造函数是否只执行一次
 */
@WebServlet("/ServletDemo2")
public class ServletDemo2 extends HttpServlet {

    /**
     * 如果servlet 多个请求触发时，只执行一次，说明servlet是单例的
     * 流程：
     *  1. tomcat加载web.xml配置
     *  2. 当发生请求来源时，如果请求是ServletDemo2 会找到 ServletDemo2 <servlet>配置
     *  3. 读取<servlet>配置，解析<servlet-class>
     *  4. 通过Java反射机制，调用 Class.forName() cls.newInstance(); 无参构造函数创建servlet对象
     *  5. 先执行 ServletDemo2 无参构造函数
     *  6. init()
     *  7. 先走service方法，进行判断 是什么请求方法，最后执行具体的请求方法
     *  8. 当tomcat被销毁时，调用destroy()
     *
     *  怎么证明servlet是单例的。servlet无参构造函数只被执行了一次，说明servlet是单例的
     *
     *  模拟一下，servlet线程不安全的问题
     */
    // 共享全局变量
    private int count = 0;

    public ServletDemo2() {
        System.out.println("ServletDemo2 构造函数");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init()");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 解决中文乱码问题
        resp.setCharacterEncoding("utf-8");// 内容编码，防止出现中文乱码
        resp.setContentType("text/html;charset=utf-8");		//向浏览器输出内容

        System.out.println("doGet()");
        System.out.println("count: " + count);
        resp.getWriter().write("我是 count: " + count);

        synchronized (ServletDemo2.class) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}
