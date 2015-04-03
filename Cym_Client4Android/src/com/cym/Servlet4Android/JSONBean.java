package com.cym.Servlet4Android;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/3.
 */
public class JSONBean implements Serializable{
    private String result;
    private String msg;
    public JSONBean(){

    }
    public JSONBean(String result,String msg){
        this.result=result;
        this.msg=msg;

    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
