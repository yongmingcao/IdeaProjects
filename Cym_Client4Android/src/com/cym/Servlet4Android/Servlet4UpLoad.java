package com.cym.Servlet4Android;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2015/4/8.
 */
@WebServlet(name = "Servlet4UpLoad")
public class Servlet4UpLoad extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---Upload--post");
        request.setCharacterEncoding("UTF-8");
        String name=request.getParameter("Name");
        String gender=request.getParameter("Gender");
        System.out.println(name);
        System.out.println(gender);
        //所有的结果都封装到request的InputStream里面
        request.getInputStream();
        //需要依赖第三方库  把不同的文件流解析出来

        //判断是否是一个复杂数据的请求


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
