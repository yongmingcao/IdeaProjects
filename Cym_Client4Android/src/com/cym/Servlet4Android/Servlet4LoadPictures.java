package com.cym.Servlet4Android;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.*;

/**
 * Created by Administrator on 2015/4/6.
 */
@WebServlet(name = "Servlet4LoadPictures")
public class Servlet4LoadPictures extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-------get--------");
        String id=request.getParameter("id");
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try {
            //文件输入流，读取磁盘上的文件
            inputStream =new FileInputStream(new File("C://Users//Administrator//Desktop//"+id+".jpg"));
            //响应输出流
            //设置响应的长度,有些客户端需要知道响应长度
            response.setContentLength((int)inputStream.available());
            response.setContentType("image/jpg");
            outputStream =response.getOutputStream();
//            byte [] b=new byte[inputStream.available()];
//            inputStream.read(b);
//            outputStream.write(b);
           byte[] b=new byte[1024];
//            //如果读完，imputStream.read(b)返回一个int数值-1，否则不是-1
//            while (inputStream.read(b)!=-1){
//                outputStream.write(b);
//            }
            int read=0;
            while ((read=inputStream.read(b))!=-1){
                outputStream.write(b,0,read);
            }

        }catch (Exception e){

        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }
}
