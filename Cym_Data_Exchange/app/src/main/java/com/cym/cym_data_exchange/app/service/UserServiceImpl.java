package com.cym.cym_data_exchange.app.service;

import android.util.Log;
import com.cym.cym_data_exchange.app.LoginActivity;
import com.cym.cym_data_exchange.app.RegisterActivity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class UserServiceImpl implements UserService {
    String TAG = "CYMLog";

    @Override
    public void register(String LoginName, List<String> interests) throws Exception {
        Thread.sleep(3000);
        Log.i(TAG,interests.toString());
        if(LoginName.equals("")){
            throw new ServiceException(RegisterActivity.MSG_REGISTER_FAILED);
        }
    }

    @Override
    public void login(String LoginName, String LoginPsw) throws Exception {

        JSONObject obj;
        JSONArray array;

        Log.i(TAG, LoginName);
        Log.i(TAG, LoginPsw);
        //Thread.sleep(3000);
//        if(LoginName.equals("Tom")&&LoginPsw.equals("123")){
//
//        }
//        else{
//            throw new ServiceException(LoginActivity.MSG_LOGIN_FAILED);
//        }
        //创建HttpClient对象
//        HttpClient client = new DefaultHttpClient();
//        /**
//         * get 传参数，又叫URL传参
//         */
//        String uri = "http://171.36.20.19:8080/Cym_Client4Android/login.do?LoginName=" + LoginName + "&LoginPsw=" + LoginPsw;
//        HttpGet get = new HttpGet(uri);
//        HttpResponse response = client.execute(get);
//        //通过response获取状态码，判断链接状态
//        int STATUS_CODE;
//        STATUS_CODE = response.getStatusLine().getStatusCode();
//        System.out.println(STATUS_CODE);
//        if (STATUS_CODE!= HttpStatus.SC_OK){
//            throw new ServiceException(LoginActivity.CONNECT_SERVER_FAILED);
//        }
        HttpParams hpar=new BasicHttpParams();
        HttpProtocolParams.setContentCharset(hpar,HTTP.UTF_8);
        //链接服务器超时时间
        HttpConnectionParams.setConnectionTimeout(hpar, 3000);
        //服务器响应超时时间
        HttpConnectionParams.setSoTimeout(hpar,3000);


        SchemeRegistry schreg=new SchemeRegistry();
        schreg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(),80));
        schreg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(),433));
        ClientConnectionManager ccm=new ThreadSafeClientConnManager(hpar,schreg);
        HttpClient client=new DefaultHttpClient(ccm,hpar);
        String uri="http://171.36.193.129:8080/Cym_Client4Android/login.do";
        HttpPost post=new HttpPost(uri);
        NameValuePair paramterLoginName=new BasicNameValuePair("LoginName",LoginName);
        NameValuePair paramterLoginPsw=new BasicNameValuePair("LoginPsw",LoginPsw);
        List paramter=new ArrayList<NameValuePair>();
        paramter.add(paramterLoginName);
        paramter.add(paramterLoginPsw);
        post.setEntity(new UrlEncodedFormEntity(paramter, HTTP.UTF_8));
        HttpResponse response=client.execute(post);

        int STATUS_CODE=response.getStatusLine().getStatusCode();

        if (STATUS_CODE == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            Log.i(TAG, result);
            if (result.equals("Success!")) {

            } else {
                throw new ServiceException(LoginActivity.MSG_LOGIN_FAILED);
            }
        }


    }
}
