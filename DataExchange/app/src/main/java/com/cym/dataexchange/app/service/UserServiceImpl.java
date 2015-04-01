package com.cym.dataexchange.app.service;

import android.util.Log;
import com.cym.dataexchange.app.LoginActivity;

/**
 * Created by Administrator on 2015/3/24.
 */
public class UserServiceImpl implements UserService {
    String TAG="CYMLog";
    @Override
    public void login(String LoginName, String LoginPsw) throws Exception {

        Log.i(TAG,LoginName);
        Log.i(TAG,LoginPsw);
        Thread.sleep(3000);
        if(LoginName.equals("Tom")&&LoginPsw.equals("123")){

        }
        else{
            throw new ServiceException(LoginActivity.MSG_LOGIN_FAILED);
        }
    }
}
