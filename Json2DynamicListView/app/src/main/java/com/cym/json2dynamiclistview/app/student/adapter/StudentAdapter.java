package com.cym.json2dynamiclistview.app.student.adapter;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cym.json2dynamiclistview.app.R;
import com.cym.json2dynamiclistview.app.student.entity.Student;

import java.util.List;

import static com.cym.json2dynamiclistview.app.R.id.textViewName;

/**
 * Created by Administrator on 2015/4/4.
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    /**
     *
     * @param context
     * @param resource   listViewChild     lalyout
     * @param objects    数据源
     */
    private LayoutInflater mInflater;
    private int resource;


    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.mInflater=LayoutInflater.from(context);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view=null;
        if(convertView==null){
            view= (LinearLayout) mInflater.inflate(resource,null);
        }
        else {
            view= (LinearLayout) convertView;
        }


        Student student=getItem(position);
        TextView txtId= (TextView) view.findViewById(R.id.textViewId);
        TextView txtName= (TextView) view.findViewById(textViewName);
        TextView txtAge= (TextView) view.findViewById(R.id.textViewAge);
        txtId.setText(student.getId().toString());
        txtName.setText(student.getName());
        txtAge.setText(String.valueOf(student.getAge()));
        return view;
    }
}
