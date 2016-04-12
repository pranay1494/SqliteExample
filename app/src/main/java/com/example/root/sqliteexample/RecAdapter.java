package com.example.root.sqliteexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by root on 16/3/16.
 */
public class RecAdapter extends RecyclerView.Adapter {
    List<Employee> employeeList;
    public RecAdapter() {
    }

    public RecAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new CardzviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Employee e = new Employee();
        e = employeeList.get(position);
        CardzviewHolder cardzviewHolder = (CardzviewHolder) holder;
        cardzviewHolder.name.setText(e.getName());
        cardzviewHolder.empcode.setText(""+e.getEmpcode());
        cardzviewHolder.department.setText(e.getDepartment());
        cardzviewHolder.designation.setText(e.getDesignation());
        cardzviewHolder.id.setText(""+e.get_id());
        cardzviewHolder.tagline.setText(e.getTagline());
        byte[] outImage=e._image;
        if(outImage!=null) {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            cardzviewHolder.img.setImageBitmap(theImage);
        }
        }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class CardzviewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView designation;
        TextView tagline;
        TextView department;
        TextView empcode;
        TextView id;
        ImageView img;
        public CardzviewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            designation = (TextView) itemView.findViewById(R.id.designation);
            tagline = (TextView) itemView.findViewById(R.id.tagline);
            department = (TextView) itemView.findViewById(R.id.department);
            empcode = (TextView) itemView.findViewById(R.id.empcode);
            id= (TextView) itemView.findViewById(R.id.eid);
            img = (ImageView) itemView.findViewById(R.id.empimg);
        }
    }
}
