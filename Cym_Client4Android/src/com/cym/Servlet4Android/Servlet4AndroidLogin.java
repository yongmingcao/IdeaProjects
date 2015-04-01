package com.cym.Servlet4Android;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2015/3/24.
 */
@WebServlet(name = "Servlet4AndroidLogin")
public class Servlet4AndroidLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        String name=request.getParameter("LoginName");
        String psw=request.getParameter("LoginPsw");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // System.out.println(name);
        //System.out.println(psw);
        PrintWriter out=null;
        try {
            out=response.getWriter();
            if(name.equals("Tom")&&psw.equals("123")){
                out.print("Success!");
            }
            else{
                out.print("Failed!");
            }
        }
        finally {
            if(out!=null){
                out.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        this.doPost(request,response);
    }
}
