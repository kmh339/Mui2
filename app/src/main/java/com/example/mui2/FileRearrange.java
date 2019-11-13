package com.example.mui2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class FileRearrange extends AppCompatActivity {

    String delFilePath = null; // MainActivity에서 삭제할 file의 경로를 받을 String 변수 선언

    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_main);

        final Intent receiveIntent = getIntent(); // MainActivity에서 intent를 받음
        delFilePath = receiveIntent.getStringExtra("delFilePath"); // MainActivity의 intent에서 추가한 data 참조
        /* 이하 AlertDialog 활용 */
        AlertDialog.Builder builder = new AlertDialog.Builder(FileRearrange.this);
        builder.setTitle("파일을 삭제하시겠습니까?");
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // 해당 AlertDialog의 dismiss() 및
                finish(); // receiveIntent의 finish();
            }
        });
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File file = new File(delFilePath);
                file.delete(); // receive 받은 data를 활용해 file 삭제 후
                finish(); // receiveIntent의 finish();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}