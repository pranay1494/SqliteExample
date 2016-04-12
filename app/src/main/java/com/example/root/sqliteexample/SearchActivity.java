package com.example.root.sqliteexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by root on 16/3/16.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    Button s_empcode;
    Button s_empname;
    Button s_emp_designation;
    EditText s_edt;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        s_edt = (EditText) findViewById(R.id.s_edt);
        s_empcode = (Button) findViewById(R.id.searcheid);
        s_empname = (Button) findViewById(R.id.searchempname);
        s_emp_designation = (Button) findViewById(R.id.searchdesignation);
        txt = (TextView) findViewById(R.id.txt123);
        s_empcode.setOnClickListener(this);
        s_empname.setOnClickListener(this);
        s_emp_designation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.searcheid)
        {
            Employee e = MainActivity.dbHandler.getEmpID(Integer.parseInt(s_edt.getText().toString()));
            txt.setText("Employee Name: "+e.getName()+"\n" +"Employee Department: "+e.getDepartment()+"\n"+ "Employee Tagline: "+e.getTagline());
        }
        else if (v.getId()==R.id.searchempname)
        {
            Employee e = MainActivity.dbHandler.getEmpName(s_edt.getText().toString());
            txt.setText("Employee Name: "+e.getName()+"\n" +"Employee Department: "+e.getDepartment()+"\n"+ "Employee Tagline: "+e.getTagline());
        }
        else if(v.getId()==R.id.searchdesignation) {
            Employee e = MainActivity.dbHandler.getEmpDesignation(s_edt.getText().toString());
            txt.setText("Employee Name: "+e.getName()+"\n" +"Employee Department: "+e.getDepartment()+"\n"+ "Employee Tagline: "+e.getTagline());
        }
    }
}
