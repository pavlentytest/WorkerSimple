package ru.samsung.itschool.mdev.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    public static final String KEY_IMAGE_URI = "output";
    private Button btn;
    OneTimeWorkRequest work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                work = new OneTimeWorkRequest.Builder(MyWorker.class).build();
                WorkManager.getInstance(getApplicationContext()).enqueue(work);
                WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(work.getId())
                        .observe(MainActivity.this, new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                if (workInfo != null) {
                                    Log.d("RRR", "WorkInfo received: state: " + workInfo.getState());
                                    String message = workInfo.getOutputData().getString("keyA");
                                    int i = workInfo.getOutputData().getInt("keyB", 0);
                                    Log.d("RRR", "message: " + message + " " + i);
                                }
                            }
                        });

            }
        });


    }


}