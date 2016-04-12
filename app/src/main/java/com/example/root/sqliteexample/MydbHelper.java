package com.example.root.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16/3/16.
 */
public class MydbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "employees.db";
    public static final String TABLE_EMPLOYEES = "employees";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMPNO = "empcode";
    public static final String COLUMN_TAGLINE = "tagline";
    public static final String COLUMN_DESIGNATION = "designation";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMG = "_img";
    public static final String COLUMN_DEPARTMENT = "department";
    private static final String TAG = MydbHelper.class.getSimpleName();


    public MydbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_EMPLOYEES + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMPNO + " INTEGER, "
                + COLUMN_DEPARTMENT + " TEXT, "
                + COLUMN_TAGLINE + " TEXT, "
                + COLUMN_DESIGNATION + " TEXT, "
                + COLUMN_IMG + " BLOB "
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }
    public void addDetails(Employee employee)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,employee.getName());
        contentValues.put(COLUMN_EMPNO,employee.getEmpcode());
        contentValues.put(COLUMN_DEPARTMENT,employee.getDepartment());
        contentValues.put(COLUMN_TAGLINE,employee.getTagline());
        contentValues.put(COLUMN_DESIGNATION,employee.getDesignation());
        contentValues.put(COLUMN_IMG, employee.get_image());
        db.insert(TABLE_EMPLOYEES, null, contentValues);
        db.close();
    }

    public List<Employee> getallEmployees()
    {
        List<Employee> employees = new ArrayList<Employee>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_EMPLOYEES + " WHERE 1";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() <1){
            Log.e(TAG, "no rows found");
            return null;
        }
        cursor.moveToFirst();

            while(!cursor.isAfterLast())
            {
                Employee employee = new Employee();
                employee.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                employee.setEmpcode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EMPNO))));
                employee.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)));
                employee.setTagline(cursor.getString(cursor.getColumnIndex(COLUMN_TAGLINE)));
                employee.setDesignation(cursor.getString(cursor.getColumnIndex(COLUMN_DESIGNATION)));
                employee.set_image(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
                employees.add(employee);
                cursor.moveToNext();
            }
        db.close();
        return employees;
    }

    Employee getEmpID(int eid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYEES, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_EMPNO, COLUMN_DEPARTMENT, COLUMN_TAGLINE,
                        COLUMN_DESIGNATION, COLUMN_IMG}, COLUMN_EMPNO + "=?",
                new String[]{String.valueOf(eid)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Employee employee = new Employee();
        employee.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
        employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        employee.setEmpcode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EMPNO))));
        employee.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)));
        employee.setTagline(cursor.getString(cursor.getColumnIndex(COLUMN_TAGLINE)));
        employee.setDesignation(cursor.getString(cursor.getColumnIndex(COLUMN_DESIGNATION)));
        employee.set_image(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
        return employee;
    }
    Employee getEmpName(String s)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYEES,new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_EMPNO,COLUMN_DEPARTMENT,COLUMN_TAGLINE,
                        COLUMN_DESIGNATION,COLUMN_IMG},COLUMN_NAME+ "=?",
                new String[] { s}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Employee employee = new Employee();
        employee.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
        employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        employee.setEmpcode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EMPNO))));
        employee.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)));
        employee.setTagline(cursor.getString(cursor.getColumnIndex(COLUMN_TAGLINE)));
        employee.setDesignation(cursor.getString(cursor.getColumnIndex(COLUMN_DESIGNATION)));
        employee.set_image(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
        return employee;
    }

    Employee getEmpDesignation(String s)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYEES,new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_EMPNO,COLUMN_DEPARTMENT,COLUMN_TAGLINE,
                        COLUMN_DESIGNATION,COLUMN_IMG},COLUMN_DESIGNATION+ "=?",
                new String[] { s }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Employee employee = new Employee();
        employee.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
        employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        employee.setEmpcode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EMPNO))));
        employee.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)));
        employee.setTagline(cursor.getString(cursor.getColumnIndex(COLUMN_TAGLINE)));
        employee.setDesignation(cursor.getString(cursor.getColumnIndex(COLUMN_DESIGNATION)));
        employee.set_image(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
        return employee;
    }
}
