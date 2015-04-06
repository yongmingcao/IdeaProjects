package com.cym.json2dynamiclistview.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import com.cym.json2dynamiclistview.app.service.ServiceException;
import com.cym.json2dynamiclistview.app.service.UserService;
import com.cym.json2dynamiclistview.app.service.UserServiceImpl;
import com.cym.json2dynamiclistview.app.student.adapter.StudentAdapter;
import com.cym.json2dynamiclistview.app.student.entity.Student;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class StudentActivity extends ActionBarActivity {
    private ListView listViewStudent;
    private List<Student> students;
    private StudentAdapter adapter;
    private static final int FLAG_STUDENT_SUCCESS=1;
    UserService userService=new UserServiceImpl();
    //private static final String LOAD_SUCCESS="加载成功！";
    private static ProgressDialog mProgressDialog;
    public static String LOADIND_DATA_ERROR="加载数据错误！";
    public void showTips(String s){
        Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
    }
    static class MyHandler extends Handler {
        final WeakReference<Activity> mActivity;

        public MyHandler(StudentActivity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            int flag = msg.what;
            Log.i("cymLog", flag + "");

            switch (flag) {
                case 0:
                    String errorMsg = msg.getData().getSerializable("ErrorMsg").toString();
                    ((StudentActivity) mActivity.get()).showTips(errorMsg);
                    break;
               case FLAG_STUDENT_SUCCESS:
                   ((StudentActivity) mActivity.get()).loadDataListView();

                  break;
            }
        }
    }
    private void loadDataListView(){
        adapter=new StudentAdapter(this,R.layout.listview_child_layout,students);
        listViewStudent.setAdapter(adapter);
    }

    MyHandler handler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity_layout);
        Log.i("CYM","YUNXNG");
        listViewStudent= (ListView) findViewById(R.id.listViewStudent);
        students=new ArrayList<Student>();
        /*students.add(new Student(101L,"Jack",20));
        students.add(new Student(102L,"Tom",25));
        students.add(new Student(103L,"Lisa",23));
        students.add(new Student(104L,"Jason",22));*/
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(StudentActivity.this);
            mProgressDialog.setTitle("请等待");
            mProgressDialog.setMessage("正在加载...");
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
        }
        /**
         * 开副线程，获取数据
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    students=userService.getStudents();
                    handler.sendEmptyMessage(FLAG_STUDENT_SUCCESS);
                }catch (ServiceException e){
                    e.printStackTrace();
                    Message msg=new Message();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("ErrorMsg",e.getMessage());
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    Log.i("Exception","异常");
                    Log.i("Exception",e.getMessage());

                    bundle.putSerializable("ErrorMsg", LOADIND_DATA_ERROR);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
        }).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
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
