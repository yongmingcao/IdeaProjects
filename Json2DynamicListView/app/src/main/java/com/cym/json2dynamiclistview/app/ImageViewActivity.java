package com.cym.json2dynamiclistview.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import com.cym.json2dynamiclistview.app.service.ServiceException;
import com.cym.json2dynamiclistview.app.service.UserService;
import com.cym.json2dynamiclistview.app.service.UserServiceImpl;

/**
 * Created by Administrator on 2015/4/7.
 */
public class ImageViewActivity extends Activity {
    public static String FAILED="请求服务器出错！";
    UserService userService=new UserServiceImpl();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview_layout);
        final ImageView imageView= (ImageView) findViewById(R.id.imageView1);
      //imageView.setImageResource(R.drawable.p1);
        //imageView.setImageAlpha(50);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                   final Bitmap bitmap= userService.getImage();
                    if(bitmap!=null){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (final ServiceException e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ImageViewActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ImageViewActivity.this,"载入远程图片失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).start();
    }
}
