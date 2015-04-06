package com.cym.Servlet4Android;

import com.cym.Servlet4Android.entity.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/6.
 */
@WebServlet(name = "Servlet4GetStudent")
public class Servlet4GetStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students=new ArrayList<Student>();
        students.add(new Student(101L,"Jack",20));
        students.add(new Student(102L,"Tom",25));
        students.add(new Student(103L,"Lisa",23));
        students.add(new Student(104L,"Jason",22));
        students.add(new Student(105L,"YEYE",29));
        students.add(new Student(106L,"NANINAI",35));
        students.add(new Student(107L,"BABA",19));
        students.add(new Student(108L,"GUUG",25));
        //转换JSON格式
        JSONArray array=JSONArray.fromObject(students);
        System.out.println(array.toString());
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=null;
        try {
            out=response.getWriter();



            //System.out.println(array.toString());

            out.print(array.toString());
        }
        finally {
            if(out!=null){
                out.close();
            }

        }


    }
}
