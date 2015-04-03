package com.cym.cym_data_exchange.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;
import com.cym.cym_data_exchange.app.service.ServiceException;
import com.cym.cym_data_exchange.app.service.UserService;
import com.cym.cym_data_exchange.app.service.UserServiceImpl;
import org.apache.http.conn.ConnectTimeoutException;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import static com.cym.cym_data_exchange.app.R.id.buttonConfirmRegister;
import static com.cym.cym_data_exchange.app.R.id.checkboxMusic;

/**
 * Created by Administrator on 2015/3/30.
 */
public class RegisterActivity extends Activity {

    private static final int FLAG_REGISTER_SUCCESS_WHAT=1;
    private  static final String FLAG_REGISTER_FAILED_VALUE_TO_KEY="注册出错！";
    private  static final String FLAG_REGISTER_SUCCESS="注册成功！";
    public static final String MSG_REGISTER_FAILED="登录名不能为空！";
    public static final String REGISTER_FAILED="登陆服务器错误！";
    public static final String TIMEOUT_CONNECT="连接服务器超时！";
    public static final String TIMEOUT_RESPONSE="服务器响应超时！";


    private EditText editTextRegisterName;
    private CheckBox chkGame;
    UserService userService=new UserServiceImpl();
    private CheckBox chkMusic;
    private CheckBox chkSports;
    private Button btnRegisterConfirm;
    private static ProgressDialog mProgressDialog;

    static class MyHandler extends Handler {
        final WeakReference<Activity> mActivity;

        public MyHandler(RegisterActivity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            int flag = msg.what;
            Log.i("cymLog",flag+"");

            switch (flag) {
                case 0:
                    String errorMsg = msg.getData().getSerializable("ErrorMsg").toString();
                    ((RegisterActivity) mActivity.get()).showTips(errorMsg);
                    break;
                case FLAG_REGISTER_SUCCESS_WHAT:
                    ((RegisterActivity) mActivity.get()).showTips(FLAG_REGISTER_SUCCESS);
                    break;
            }
        }
    }

    MyHandler handler = new MyHandler(this);

    public void initViews() {
        editTextRegisterName = (EditText) findViewById(R.id.editTextRegisterName);
        chkGame = (CheckBox) findViewById(R.id.checkboxGame);
        chkMusic = (CheckBox) findViewById(checkboxMusic);
        chkSports = (CheckBox) findViewById(R.id.checkboxSports);
        btnRegisterConfirm = (Button) findViewById(R.id.buttonConfirmRegister);

    }
    public void showTips(String s){
        Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initViews();
        btnRegisterConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameS = editTextRegisterName.getText().toString();
                final List<String> interests = new ArrayList<String>();
                if (chkGame.isChecked()) {
                    interests.add(chkGame.getText().toString());
                }
                if (chkMusic.isChecked()) {
                    interests.add(chkMusic.getText().toString());
                }
                if (chkSports.isChecked()) {
                    interests.add(chkSports.getText().toString());

                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialog(RegisterActivity.this);
                    mProgressDialog.setTitle("请等待");
                    mProgressDialog.setMessage("正在注册...");
                    mProgressDialog.setCancelable(true);
                    mProgressDialog.show();
                }
                Thread mThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            userService.register(nameS,interests);
                            //然后，通过handler向主线程发送消息
                            handler.sendEmptyMessage(FLAG_REGISTER_SUCCESS_WHAT);

                        }
                        catch (ConnectTimeoutException e){
                            e.printStackTrace();
                            Message msg=new Message();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("ErrorMsg",TIMEOUT_CONNECT);
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                        catch (SocketTimeoutException e){
                            e.printStackTrace();
                            Message msg=new Message();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("ErrorMsg",TIMEOUT_RESPONSE);
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                        catch (ServiceException e){
                            e.printStackTrace();
                            Message msg=new Message();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("ErrorMsg",e.getMessage());
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                        //捕获业务逻辑
                        catch(Exception e) {
                            e.printStackTrace();
                            Message msg = new Message();
                            Bundle bundle = new Bundle();
                            Log.i("Exception","异常");
                            bundle.putSerializable("ErrorMsg", FLAG_REGISTER_FAILED_VALUE_TO_KEY);
                            msg.setData(bundle);
                            handler.sendMessage(msg);

                        }
                    }
                };
                mThread.start();
            }
        });


    }
}
