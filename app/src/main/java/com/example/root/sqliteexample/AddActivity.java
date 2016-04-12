package com.example.root.sqliteexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by root on 16/3/16.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener{

   // EditText nameEdt;
    EditText EmpCodeEdt;
    EditText designationEdt;
    EditText TaglineEdt;
    EditText DepartmentEdt;
    Button submit;
    byte img[];
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int PICK_PHOTO = 2;
    public static final int FILE_SIZE_LIMIT = 1024 * 1024*2;
    public String qrcode;
    TextView qrtext;
    Button qrbtn;


    //    MydbHelper dbHandler;
protected DialogInterface.OnClickListener mDialogListener = new DialogInterface.OnClickListener() {

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == 0)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
        else
        {
            Intent choosePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
            choosePhotoIntent.setType("image/*");
            startActivityForResult(choosePhotoIntent, PICK_PHOTO);
        }
    }
};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

      //  nameEdt = (EditText) findViewById(R.id.nameedt);
        EmpCodeEdt = (EditText) findViewById(R.id.empcodeedt);
        designationEdt = (EditText) findViewById(R.id.designationedt);
        TaglineEdt = (EditText) findViewById(R.id.taglineedt);
        DepartmentEdt = (EditText) findViewById(R.id.departmentedt);
        submit = (Button) findViewById(R.id.addsubmit);
        qrbtn = (Button) findViewById(R.id.qrbtn);
        qrtext = (TextView) findViewById(R.id.qrtxt);
        submit.setOnClickListener(this);
        qrbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addsubmit)
        {
            Employee employee = new Employee(Qrcode.qrcode,designationEdt.getText().toString(),
                    TaglineEdt.getText().toString(),DepartmentEdt.getText().toString(),
                    Integer.parseInt(EmpCodeEdt.getText().toString()),img);
            MainActivity.dbHandler.addDetails(employee);
       //     Log.d("Employ", nameEdt.getText().toString() + EmpCodeEdt.getText().toString());
            Toast.makeText(this,"Data Added",Toast.LENGTH_SHORT).show();
           // printdb();
            finish();
        }
        if(v.getId()==R.id.qrbtn)
        {
            Intent i = new Intent(AddActivity.this,Qrcode.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.camera)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(R.array.alert, mDialogListener);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
            {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);
                img = bytes.toByteArray();
            }
            else if(requestCode==PICK_PHOTO)
            {
                int fileSize = 0;
                InputStream inputStream = null;

                Uri selectedImageUri = data.getData();
                try {
                    inputStream = getContentResolver().openInputStream(
                            selectedImageUri);
                    fileSize = inputStream.available();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    Toast.makeText(this, "error opening file",
                            Toast.LENGTH_LONG).show();
                    return;
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) { /* Intentionally blank */
                    }
                }

                if (fileSize >= FILE_SIZE_LIMIT) {
                    Toast.makeText(this,
                            "file size greater than 2mb",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                String[] fileColumn = { MediaStore.Images.Media.DATA };
                Cursor imageCursor = getContentResolver().query(selectedImageUri,
                        fileColumn, null, null, null);
                imageCursor.moveToFirst();

                int fileColumnIndex = imageCursor.getColumnIndex(fileColumn[0]);
                String picturePath = imageCursor.getString(fileColumnIndex);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bytes);
                img = bytes.toByteArray();
            }
        }
    }
}

