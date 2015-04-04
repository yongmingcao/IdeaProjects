package com.cym.json2dynamiclistview.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.cym.json2dynamiclistview.app.student.adapter.StudentAdapter;
import com.cym.json2dynamiclistview.app.student.entity.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentActivity extends ActionBarActivity {
    private ListView listViewStudent;
    private List<Student> students;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity_layout);
        listViewStudent= (ListView) findViewById(R.id.listViewStudent);
        students=new ArrayList<Student>();
        students.add(new Student(101L,"Jack",20));
        students.add(new Student(102L,"Tom",25));
        students.add(new Student(103L,"Lisa",23));
        students.add(new Student(104L,"Jason",22));
        adapter=new StudentAdapter(this,R.layout.listview_child_layout,students);
        listViewStudent.setAdapter(adapter);
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
