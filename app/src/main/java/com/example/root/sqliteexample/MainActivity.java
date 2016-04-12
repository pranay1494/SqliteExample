package com.example.root.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button add;
    Button search;
    Button browse;
  static  MydbHelper dbHandler;
    static List<Employee> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button) findViewById(R.id.add);
        search = (Button) findViewById(R.id.search);
        browse = (Button) findViewById(R.id.browse);
        add.setOnClickListener(this);
        search.setOnClickListener(this);
        browse.setOnClickListener(this);
        dbHandler = new MydbHelper(this, null, null, 1);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add)
        {
            Intent i = new Intent(MainActivity.this,AddActivity.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.search)
        {
            Intent i = new Intent(MainActivity.this,SearchActivity.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.browse)
        {
            list = dbHandler.getallEmployees();
            for(int i=0;i<list.size();i++)
            {
                Employee e = list.get(i);
                Log.d("emp",e.getName()+" "+e.getDesignation());
            }
            Intent i = new Intent(MainActivity.this,Browse.class);
            startActivity(i);
        }
    }
}
