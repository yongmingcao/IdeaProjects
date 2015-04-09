package com.cym.json2dynamiclistview.app.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.cym.json2dynamiclistview.app.ImageViewActivity;
import com.cym.json2dynamiclistview.app.StudentActivity;
import com.cym.json2dynamiclistview.app.student.entity.Student;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/3/24.
 */
public class UserServiceImpl implements UserService {
    String TAG = "CYMLog";

    @Override
    public void login(String LoginName, String LoginPsw) throws Exception {

    }

    @Override
    public void register(String LoginName, List<String> interests) throws Exception {

    }

    @Override
    public List<Student> getStudents() throws Exception {
        return null;
    }

    private static StringBuffer setPostParams(Map<String,String> params){
        StringBuffer stringBuffer=new StringBuffer();
        for (Map.Entry<String,String> entry:params.entrySet()) {
            try {
                stringBuffer.append((entry.getKey()))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer;
    }





    @Override
    public Bitmap getImage() throws Exception {
        Bitmap bitmap = null;
        URL url = null;
        InputStream inputStream = null;
        OutputStream out = null;
        byte[] data = null;
        HttpURLConnection urlConnection = null;
        try {
            Map<String,String>params=new HashMap<String, String>();
            params.put("id","2");
            data=setPostParams(params).toString().getBytes();

            String surl = "http://192.168.0.106:8080/Cym_Client4Android/getImage.do?id=2";

            //使用post请求
            url = new URL(surl);
            //打开连接
            urlConnection = (HttpURLConnection) url.openConnection();
            //连接之前设置参数
            //设置 请求超时时间
            urlConnection.setConnectTimeout(3000);
            // 设置 响应的超时时间
            urlConnection.setReadTimeout(3000);
            //封装参数
           // inputStream = urlConnection.getInputStream();
            /**
             * Http协议里面的内容
             */
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            //取消缓存
            urlConnection.setUseCaches(false);

            //         urlConnection.setRequestProperty("Content-type",newValue);
            urlConnection.connect();
            out = urlConnection.getOutputStream();
            out.write(data);
            //刷新 数据
            out.flush();

            int responseCode = urlConnection.getResponseCode();
            System.out.println(responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {

            } else {
                throw new ServiceException(ImageViewActivity.FAILED);
            }
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            if (inputStream != null) {
                bitmap = BitmapFactory.decodeStream(inputStream);


//            String surl="http://192.168.0.106:8080/Cym_Client4Android/getImage.do?id=2";
//            url=new URL(surl);
//            urlConnection= (HttpURLConnection) url.openConnection();
//            urlConnection.setDoInput(true);//设置 可以读取数据，可以读取服务器发来的数据
//            //urlConnection.setDoOutput(true);//设置 向服务器写数据
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//            int responseCode=urlConnection.getResponseCode();
//            System.out.println(responseCode);
//            if(responseCode==HttpURLConnection.HTTP_OK){
//
//            }
//            else{
//                throw new ServiceException(ImageViewActivity.FAILED);
//            }
//            inputStream=urlConnection.getInputStream();
//            if(inputStream!=null){
//                bitmap= BitmapFactory.decodeStream(inputStream);
                // }
            }
        }finally{
                //关闭和服务器的链接
                if (inputStream != null) {
                    inputStream.close();
                }
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return bitmap;



//        public List<Student> getStudents ()throws Exception {
//            List<Student> students = new ArrayList<Student>();
//            HttpClient client = new DefaultHttpClient();
//            String uri = "http://192.168.0.106:8080/Cym_Client4Android/getStudent.do";
//            HttpGet get = new HttpGet(uri);
//            HttpResponse response = client.execute(get);
//            int STATUS_CODE = response.getStatusLine().getStatusCode();
//
//            if (STATUS_CODE == HttpStatus.SC_OK) {
//                String result = EntityUtils.toString(response.getEntity());
//                Log.i(TAG, result);
//                /**
//                 * 解析result
//                 */
//                JSONArray array = new JSONArray(result);
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject obj = array.getJSONObject(i);
//                    Long id = obj.getLong("id");
//                    String name = obj.getString("name");
//                    int age = obj.getInt("age");
//                    students.add(new Student(id, name, age));
//                }
//
//
//            } else {
//                throw new ServiceException(StudentActivity.LOADIND_DATA_ERROR);
//            }
//
//            return students;
//        }
//
//
//       // public void register(String LoginName, List < String > interests)throws Exception{
////        Thread.sleep(3000);
////        Log.i(TAG,interests.toString());
////        if(LoginName.equals("http://192.168.0.106:8080/Cym_Client4Android/register.do")){
////            throw new ServiceException(RegisterActivity.MSG_REGISTER_FAILED);
////        }
////        HttpClient client=new DefaultHttpClient();
////        String uri="http://192.168.0.106:8080/Cym_Client4Android/register.do";
////        HttpPost post=new HttpPost(uri);
//
//            /**
//             * 客户端封装JSON数据
//             */
////
////        JSONObject obj=new JSONObject();
////        obj.put("LoginName",LoginName);
////        JSONArray array=new JSONArray();
////        if(interests!=null){
////            for(String string:interests){
////                array.put(string);
////            }
////        }

//        obj.put("interests",array);
//        NameValuePair paramter=new BasicNameValuePair("Data",obj.toString());
//        List<NameValuePair> list=new ArrayList<NameValuePair>();
//        list.add(paramter);
//        post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
//        HttpResponse response=client.execute(post);
//       // Log.i("Cym",obj.toString());
//
//        int STATUS_CODE=response.getStatusLine().getStatusCode();
//
//        if (STATUS_CODE == HttpStatus.SC_OK) {
//            String results = EntityUtils.toString(response.getEntity(),"utf-8");
//            Log.i("cym",results);
//            /**
//             * 解析JSON
//             */
//
//            JSONObject object=new JSONObject(results);
//            String result=object.getString("result");
//
//            if(result.equals("success")){
//                //注册success
//
//            }
//            else{
//                //注册failed
//                String msg=object.getString("msg");
//                throw  new ServiceException(msg);
//            }
//        }
//        else{
//            throw new ServiceException(RegisterActivity.REGISTER_FAILED);
//        }
       // }


       // public void login (String LoginName, String LoginPsw)throws Exception {

//        JSONObject obj;
//        JSONArray array;
//
//        Log.i(TAG, LoginName);
//        Log.i(TAG, LoginPsw);
//        //Thread.sleep(3000);
////        if(LoginName.equals("Tom")&&LoginPsw.equals("123")){
////
////        }
////        else{
////            throw new ServiceException(LoginActivity.MSG_LOGIN_FAILED);
////        }
//        //创建HttpClient对象
////        HttpClient client = new DefaultHttpClient();
////        /**
////         * get 传参数，又叫URL传参
////         */
////        String uri = "http://171.36.20.19:8080/Cym_Client4Android/login.do?LoginName=" + LoginName + "&LoginPsw=" + LoginPsw;
////        HttpGet get = new HttpGet(uri);
////        HttpResponse response = client.execute(get);
////        //通过response获取状态码，判断链接状态
////        int STATUS_CODE;
////        STATUS_CODE = response.getStatusLine().getStatusCode();
////        System.out.println(STATUS_CODE);
////        if (STATUS_CODE!= HttpStatus.SC_OK){
////            throw new ServiceException(LoginActivity.CONNECT_SERVER_FAILED);
////        }
//        HttpParams hpar=new BasicHttpParams();
//        HttpProtocolParams.setContentCharset(hpar,HTTP.UTF_8);
//        //链接服务器超时时间
//        HttpConnectionParams.setConnectionTimeout(hpar, 3000);
//        //服务器响应超时时间
//        HttpConnectionParams.setSoTimeout(hpar,3000);
//
//
//        SchemeRegistry schreg=new SchemeRegistry();
//        schreg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(),80));
//        schreg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(),433));
//        ClientConnectionManager ccm=new ThreadSafeClientConnManager(hpar,schreg);
//        HttpClient client=new DefaultHttpClient(ccm,hpar);
//        String uri="http://171.36.193.129:8080/Cym_Client4Android/login.do";
//        HttpPost post=new HttpPost(uri);
//        NameValuePair paramterLoginName=new BasicNameValuePair("LoginName",LoginName);
//        NameValuePair paramterLoginPsw=new BasicNameValuePair("LoginPsw",LoginPsw);
//        List paramter=new ArrayList<NameValuePair>();
//        paramter.add(paramterLoginName);
//        paramter.add(paramterLoginPsw);
//        post.setEntity(new UrlEncodedFormEntity(paramter, HTTP.UTF_8));
//        HttpResponse response=client.execute(post);
//
//        int STATUS_CODE=response.getStatusLine().getStatusCode();
//
//        if (STATUS_CODE == HttpStatus.SC_OK) {
//            String result = EntityUtils.toString(response.getEntity());
//            Log.i(TAG, result);
//            if (result.equals("Success!")) {
//
//            } else {
//                throw new ServiceException(LoginActivity.MSG_LOGIN_FAILED);
//            }
//        }


        //}
    }
}
