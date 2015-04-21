package com.cym.servletlearning2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2015/4/10.
 */
@WebServlet(name = "Servlet4Test")
public class Servlet4Test extends HttpServlet {
    /**
     * Servlet生命周期方法
     * init（）；  对象的新建
     * destroy（）； 对象的销毁，自动调用
     * service（）；有请求时，自动调用
     * @throws ServletException
     */
    public void init() throws ServletException {
        System.out.println("init()被调用");
    }

    @Override
    public void destroy() {
        System.out.println("destroy()被调用");
    }

    /**
     * Http请求有2个，指明请求方式   GET，POST，没有只指明，自动调用doGet方法
     *
     * @param request：客户请求所封装的信息，数据，都在这里面
     * @param response：服务器端返回给用户的数据，信息 封装到这里
     * @throws ServletException
     * @throws IOException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet()被调用");
        /**
         * PrintWriter   输出流，输出到客户的
         * 浏览器上面
         *out可以输出完整的html源代码，以字符串形式输出
         *
         * 由用户的浏览器解析
         * 输出流 是由response获得的
         */

        String name=request.getParameter("userName");
//        request.getParameterMap()
//        request.getParameterNames()
//        request.getParameterValues()
        System.out.println(name);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
       out.print("HELLO"+name+"!!!");
        out.print("<h1>HELLO.MaiZi</h1>");
        out.close();
        /**
         * 浏览器输入地址，进行的HttpRequest请求
         * 服务器接受请求，进行HttpResponse响应
         * 到用户的浏览器
         *
         */



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost()被调用");
        req.setCharacterEncoding("utf-8");
        String userName=req.getParameter("userName");
        String userPsw=req.getParameter("userPsw");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out=resp.getWriter();
        out.print(userName+userPsw+"!!!!!!!!");
        out.close();
    }
}
