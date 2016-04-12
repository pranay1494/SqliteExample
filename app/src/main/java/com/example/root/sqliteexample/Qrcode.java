package com.example.root.sqliteexample;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

/**
 * Created by root on 20/3/16.
 */
public class Qrcode extends AppCompatActivity implements View.OnClickListener,QRCodeReaderView.OnQRCodeReadListener  {
    TextView textView;
    private QRCodeReaderView mydecoderview;
    public static String qrcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);

        textView= (TextView) findViewById(R.id.textView);
        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();

    }
    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrcode = text;
        Log.d("qrcode",text);
        textView.setText(qrcode);
    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }
}
