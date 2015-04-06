package com.cym.json2dynamiclistview.app.service;

import com.cym.json2dynamiclistview.app.student.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface UserService {
    public void login(String LoginName, String LoginPsw)throws Exception;
    public void register(String LoginName, List<String> interests) throws Exception;
    public List<Student>getStudents()throws Exception;
}
