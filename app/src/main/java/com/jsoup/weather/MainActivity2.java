package com.jsoup.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MainActivity2 extends AppCompatActivity {
    WebView web;
    TextView select;
    String cityToken = null;
    String cityIndex = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        web=findViewById(R.id.webv);
        select = findViewById(R.id.select);

        Bundle getData = getIntent().getExtras();
        cityToken = getData.getString("token");
        cityIndex = getData.getString("cityIndex");


        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        //web.loadUrl("https://weather.com/tr-TR/");
        //new MyWebViewClient().shouldOverrideUrlLoading(web,"https://weather.com/tr-TR/");
        //viewSource();

        web.loadUrl("https://weather.com/tr-TR/weather/hourbyhour/l/"+cityToken);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String webUrl;
                String url;
                if(web!=null){
                    webUrl = web.getUrl();
                    String array[] = webUrl.split("/");

                    for (int i=0;i<array.length;i++){

                        if (array[i].equals("l")){
                            url=array[i+1];
                            cityToken = url;
                        }
                    }
                    Intent getToken = new Intent(MainActivity2.this,MainActivity.class);
                    getToken.putExtra("cityIndex",cityIndex);
                    getToken.putExtra("token",cityToken);
                    startActivity(getToken);
                }
            }
        });

    }


}