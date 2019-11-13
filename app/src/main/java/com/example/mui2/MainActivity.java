package com.example.mui2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

        public String basePath = null;
        public GridView mGridView;
        public CustomImageAdapter mCustomImageAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "MyCameraApp");

            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("MyCameraApp", "failed to create directory");
                }
            }

            basePath = mediaStorageDir.getPath();

            mGridView = (GridView) findViewById(R.id.gridview); // .xml의 GridView와 연결
            mCustomImageAdapter = new CustomImageAdapter(this, basePath); // 앞에서 정의한 Custom Image Adapter와 연결
            mGridView.setAdapter(mCustomImageAdapter); // GridView가 Custom Image Adapter에서 받은 값을 뿌릴 수 있도록 연결
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), mCustomImageAdapter.getItemPath(position), Toast.LENGTH_LONG).show();
                }
            });
        }
}

//ee