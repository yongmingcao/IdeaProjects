package com.cym.Servlet4Android;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Created by Administrator on 2015/4/1.
 */
@WebServlet(name = "Servlet4AndroidRegister")
public class Servlet4AndroidRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        /** 数据模型
         *    data={
         "LoginName":"Tom",
         "interests":["Game","Music","Sports"]
         }
         */
        String data=request.getParameter("Data");
       // System.out.println(data);
        /**
         * 使用json-lib 解析
         */
        JSONObject obj=JSONObject.fromObject(data);
        String loginName=obj.getString("LoginName");
        System.out.println("注册的用户名是："+loginName);
        JSONArray array=obj.getJSONArray("interests");
        System.out.println("兴趣爱好有：");
        if(array!=null){
            for (Object o:array){
                System.out.print(o.toString());
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
