package com.cym.dataexchange.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.cym.dataexchange.app.service.ServiceException;
import com.cym.dataexchange.app.service.UserService;
import com.cym.dataexchange.app.service.UserServiceImpl;

import java.lang.ref.WeakReference;


public class LoginActivity extends ActionBarActivity {
    Button buttonLogin;
    Button buttonReset;
    EditText editTextName;
    EditText editTextPsw;
    static ProgressDialog mProgressDialog;
    private UserService userService=new UserServiceImpl();
    private static final int FLAG_LOGIN_SUCCESS_WHAT=1;
    private  static final String FLAG_LOGIN_FAILED_VALUE_TO_KEY="登陆出错！";
    private  static final String FLAG_LOGIN_SUCCESS="登陆成功！";
    public static final String MSG_LOGIN_FAILED="登录名或者登陆密码错误！";
//    Handler handler=new Handler(){
//        //此方法 接受副线程发来的消息，并处理
//        //用于更新UI等
//        @Override
//        public void handleMessage(Message msg) {
//
//
//        }
//    };

    static class MyHandler extends Handler{
        final WeakReference<Activity> mActivity;

        public MyHandler(LoginActivity activity){
            mActivity=new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if(mProgressDialog!=null){
                mProgressDialog.dismiss();
            }
            int flag=msg.what;

            switch (flag){
                case 0:
                    String errorMsg=msg.getData().getSerializable("ErrorMsg").toString();
                    ((LoginActivity)mActivity.get()).showTips(errorMsg);
                    break;
                case FLAG_LOGIN_SUCCESS_WHAT:
                    ((LoginActivity)mActivity.get()).showTips(FLAG_LOGIN_SUCCESS);
                    break;
            }
        }
    }
    MyHandler handler=new MyHandler(this);
    public void showTips(String s){
        Toast.makeText(getApplication(),s,Toast.LENGTH_SHORT).show();
    }
    public void initViews(){
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        buttonReset=(Button)findViewById(R.id.buttonReset);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextPsw=(EditText)findViewById(R.id.editTextPsw);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        initViews();

         buttonLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String name=editTextName.getText().toString();
                 final String psw=editTextPsw.getText().toString();
                // Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                 //Toast.makeText(getApplicationContext(),psw,Toast.LENGTH_SHORT).show();
                 if(name.equals("")|psw.equals("")){
                     Toast.makeText(getApplicationContext(),"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                 }
                 if(mProgressDialog==null){
                     mProgressDialog=new ProgressDialog(LoginActivity.this);
                     mProgressDialog.setTitle("请等待");
                     mProgressDialog.setMessage("登录中...");
                     mProgressDialog.setCancelable(true);
                     mProgressDialog.show();
                 }
                 Thread mThread=new Thread(){
                     @Override
                     public void run() {
                        try{
                            userService.login(name,psw);
                            //然后，通过handler向主线程发送消息
                            handler.sendEmptyMessage(FLAG_LOGIN_SUCCESS_WHAT);

                        }
                        //捕获业务逻辑
                        catch (ServiceException e){
                            e.printStackTrace();
                            Message msg=new Message();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("ErrorMsg",e.getMessage());
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            Message msg=new Message();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("ErrorMsg",FLAG_LOGIN_FAILED_VALUE_TO_KEY);
                            msg.setData(bundle);
                            handler.sendMessage(msg);

                        }
                     }
                 };
                 mThread.start();
             }
         });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
