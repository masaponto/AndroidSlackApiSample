package com.example.masato.slackapisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void postSlack(View view) {

        String str = "1, 2, 3\n" +
                "4, 5, 6";


        RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"), str.getBytes());

        Map<String, RequestBody> map = new HashMap<>();
        map.put("file\"; filename=\"result.csv\"", file);
        String fileName = "result.csv";
        String title = "Result";

        SlackApiHolder.get()
                .uploadFile(SlackApi.TOKEN, map, "csv", fileName, title, "", SlackApi.CHANNEL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SlackApi.UploadFileResponse>() {
                    @Override
                    public void call(SlackApi.UploadFileResponse uploadFileResponse) {
                        Toast.makeText(MainActivity.this, "slack post done!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
