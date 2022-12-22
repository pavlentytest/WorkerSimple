package ru.samsung.itschool.mdev.myapplication;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

     public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

        @NonNull
    @Override
    public Result doWork() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Data output = new Data.Builder()
                    .putString("keyA", "Hello")
                    .putInt("keyB", 10)
                    .build();
            return Worker.Result.success(output);
    }
}
