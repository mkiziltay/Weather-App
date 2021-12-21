package com.jsoup.weather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
        // null olması halinde

    SharedPreferences sharedPreferences;
    String cityIndex, cityToken;
    Bundle getData;

    public static String baseUrl = "https://weather.com/tr-TR/weather/tenday/l/";
    public static String cityOneToken ;
    public static String citySecondToken ;
    public static String cityThirdToken ;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    TextView cityOne,cityTwo,cityThree;
    ListView oListV;
    ListView oListV2;
    ListView oListV3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(true);
        dialogBuilder.setView(R.layout.dialog);
        dialog = dialogBuilder.create();
        dialog.show();

        getData = getIntent().getExtras();
        if (!(getData==null)) { Log.i("geturl","Bundle NOT null");
            getIntentDatas();
        }

        sharedPreferences = getApplicationContext().getSharedPreferences("cityTokens",MODE_PRIVATE); //loadData metodundaki sharedPrefs. çağrılıyor.
        if (sharedPreferences.getAll().isEmpty()){
        Log.i("geturl","sharedPreferences is null");
        loadData(); // ve sonra sharedPrefs den bilgileri alınıyor.
            cityOneToken = sharedPreferences.getString("first","");
            citySecondToken = sharedPreferences.getString("second","");
            cityThirdToken = sharedPreferences.getString("third","");
        }else{
            Log.i("geturl","sharedPreferences NOT null");
            cityOneToken = sharedPreferences.getString("first","");
            citySecondToken = sharedPreferences.getString("second","");
            cityThirdToken = sharedPreferences.getString("third","");
            // Edit or clear DATA => SharedPreferences.Editor editor = sharedPreferences.edit(); editor.clear(); editor.commit();
        }


        oListV=findViewById(R.id.listone);
        oListV2=findViewById(R.id.listtwo);
        oListV3=findViewById(R.id.listthre);
        cityOne = findViewById(R.id.textone);
        cityTwo = findViewById(R.id.texttwo);
        cityThree = findViewById(R.id.textthre);
        new getHtml().execute();
        changeCity();
    }


    public class getHtml extends AsyncTask<Void,Void,Void> {

        Document doc ;
        Document docfl ;
        Document docgl ;
        Elements el1 ;
        String[] listDay= new String[15]; String[] listDegree= new String[15]; String[] listForecast= new String[15]; String[] listMoisture= new String[15];
        String[] listDayB= new String[15]; String[] listDegreeB= new String[15]; String[] listForecastB= new String[15]; String[] listMoistureB= new String[15];
        String[] listDayC= new String[15]; String[] listDegreeC= new String[15]; String[] listForecastC = new String[15]; String[] listMoistureC= new String[15];

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cityOne.setText(doc.select("main").select("h1").attr("span","LocationPageTitle--PresentationName--1QYny").text().split("-")[1].split(",")[0]);
            WeatherAdapter oWeatherAdp = new WeatherAdapter( MainActivity.this,listDay,listDegree,listForecast,listMoisture );
            oListV.setAdapter(oWeatherAdp);
            oListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String desc = oWeatherAdp.forecastArray[i];
                    oListV3.setSelection(i);
                    oListV2.setSelection(i);
                    oListV.setSelection(i);
                    Toast.makeText(MainActivity.this, desc, Toast.LENGTH_SHORT).show();
                }
            });
            //ADAPTER 2
            cityTwo.setText(docfl.select("main").select("h1").attr("span","LocationPageTitle--PresentationName--1QYny").text().split("-")[1].split(",")[0]);
            WeatherAdapter oWeatherAdp2 = new WeatherAdapter( MainActivity.this,listDayB,listDegreeB,listForecastB,listMoistureB );
            oListV2.setAdapter(oWeatherAdp2);
            oListV2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String desc = oWeatherAdp2.forecastArray[i];
                    oListV3.setSelection(i);
                    oListV2.setSelection(i);
                    oListV.setSelection(i);
                    Toast.makeText(MainActivity.this, desc, Toast.LENGTH_SHORT).show();
                }
            });
            //ADAPTER 3
            cityThree.setText(docgl.select("main").select("h1").attr("span","LocationPageTitle--PresentationName--1QYny").text().split("-")[1].split(",")[0]);
            WeatherAdapter oWeatherAdp3 = new WeatherAdapter( MainActivity.this,listDayC,listDegreeC,listForecastC,listMoistureC );
            oListV3.setAdapter(oWeatherAdp3);
            oListV3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String desc = oWeatherAdp3.forecastArray[i];
                    oListV3.setSelection(i);
                    oListV2.setSelection(i);
                    oListV.setSelection(i);
                    Toast.makeText(MainActivity.this, desc, Toast.LENGTH_SHORT).show();
                }
            });

         dialog.dismiss();
        Log.i("gethtml","COMP----LE------TED------");
        oListV.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem < firstVisibleItem){
                    Log.i("TAG"," "+(firstVisibleItem-mLastFirstVisibleItem)+"//"+firstVisibleItem+"//"+visibleItemCount);
                    oListV3.setSelection(firstVisibleItem);
                    oListV2.setSelection(firstVisibleItem);
                }
                if(mLastFirstVisibleItem > firstVisibleItem){
                    // scrolling up
                    Log.i("TAG"," "+(mLastFirstVisibleItem-firstVisibleItem)+"//"+firstVisibleItem+"//"+visibleItemCount);
                    oListV3.setSelection(firstVisibleItem);
                    oListV2.setSelection(firstVisibleItem);
                }
                mLastFirstVisibleItem = firstVisibleItem;
            }
        });
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
//div.DailyForecast--DisclosureList--msYIJ details div h2 --> günler
//body div div.DetailsSummary--temperature--1Syw3 --> sıcaklıklar
            try { //JSOUP FOR FIRST CITY
                doc = Jsoup.connect(MainActivity.baseUrl+cityOneToken+"#detailIndex14").get();
                Elements el2 = doc.select("body").select("div").first().getElementsByClass("DailyForecast--DisclosureList--msYIJ").select("details");
                //JSOUP FOR SECOND CITY
                docfl = Jsoup.connect(MainActivity.baseUrl+citySecondToken).get();
                Elements fl2 = docfl.select("body").select("div").first().getElementsByClass("DailyForecast--DisclosureList--msYIJ").select("details");
                //JSOUP FOR THIRD CITY
                docgl = Jsoup.connect(MainActivity.baseUrl+cityThirdToken).get();
                Elements gl2 = docgl.select("body").select("div").first().getElementsByClass("DailyForecast--DisclosureList--msYIJ").select("details");

                for (int i=0;i<el2.select("div").select("h2").size();i++) { //--------------- FIRST
                    //Log.i("gethtml", "//"+el2.select("div").attr("class","DetailsSummary--condition--24gQw").get(i).select("span").text());
                    //Log.i("gethtml", "Day**"+el2.select("div").select("h2").get(i).text());
                    listDay[i] =el2.select("div").select("h2").get(i).text();
                    //Log.i("gethtml", "Degree**"+el2.attr("class","DetailsSummary--temperature--1Syw3").get(i).child(0).text().replace("g"," ") .split(" ")[2] );
                    listDegree[i] = el2.attr("class","DetailsSummary--temperature--1Syw3").get(i).child(0).text().replace("gü"," ").split(" ")[2];
                    //Log.i("gethtml", "Forecast**"+el2.attr("data-testid","PercentageValue").get(i).select("span").get(3).text());
                    listForecast[i] = el2.attr("data-testid","PercentageValue").get(i).select("span").get(3).text();
                    //Log.i("gethtml", "Moisture**"+el2.attr("data-testid","PercentageValue").get(i).select("span").get(4).text());
                    listMoisture[i] = el2.attr("data-testid","PercentageValue").get(i).select("span").get(4).text();
                }


                for (int i=0;i<fl2.select("div").select("h2").size();i++) { //--------------- SECOND
                    listDayB[i] =fl2.select("div").select("h2").get(i).text();
                    listDegreeB[i] = fl2.attr("class","DetailsSummary--temperature--1Syw3").get(i).child(0).text().replace("gü"," ").split(" ")[2];
                    listForecastB[i] = fl2.attr("data-testid","PercentageValue").get(i).select("span").get(3).text();
                    listMoistureB[i] = fl2.attr("data-testid","PercentageValue").get(i).select("span").get(4).text();
                }

                for (int i=0;i<gl2.select("div").select("h2").size();i++) { //--------------- THIRD
                    listDayC[i] =gl2.select("div").select("h2").get(i).text();
                    listDegreeC[i] = gl2.attr("class","DetailsSummary--temperature--1Syw3").get(i).child(0).text().replace("gü"," ") .split(" ")[2];
                    listForecastC[i] = gl2.attr("data-testid","PercentageValue").get(i).select("span").get(3).text();
                    listMoistureC[i] = gl2.attr("data-testid","PercentageValue").get(i).select("span").get(4).text();
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.i("gethtml","ERROR"+e.toString());
            }
        return null;
        }

    }

    private void changeCity() {
        cityOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this,MainActivity2.class);
                cityIntent.putExtra("token",cityOneToken);
                cityIntent.putExtra("cityIndex","first");
                startActivity(cityIntent);
            }
        });

        cityTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this,MainActivity2.class);
                cityIntent.putExtra("token",citySecondToken);
                cityIntent.putExtra("cityIndex","second");
                startActivity(cityIntent);
            }
        });

        cityThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this,MainActivity2.class);
                cityIntent.putExtra("token",cityThirdToken);
                cityIntent.putExtra("cityIndex","third");
                startActivity(cityIntent);
            }
        });

    }

    public void loadData(){
        sharedPreferences =getSharedPreferences("cityTokens", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "first", "f38419bdba5b880763808b472bd412cc03b64a36a7f21968ced9c4976789813c");
        editor.putString( "second", "f38419bdba5b880763808b472bd412cc03b64a36a7f21968ced9c4976789813c");
        editor.putString( "third", "f38419bdba5b880763808b472bd412cc03b64a36a7f21968ced9c4976789813c");
        editor.commit();
        Log.i("geturl",sharedPreferences.getString("first","")+"////////");

    }

    private void getIntentDatas() {
        SharedPreferences.Editor editor = getSharedPreferences("cityTokens", MODE_PRIVATE).edit();
        Bundle getData =getIntent().getExtras();
        cityIndex=getData.getString("cityIndex");
        cityToken=getData.getString("token");
        Log.i("geturl",cityIndex+" : "+cityToken);
        if (cityIndex.equals("first")){
            editor.putString(cityIndex,cityToken);
            editor.apply();
            editor.commit();
            Log.i("geturl","THE fst CITY HAS BEEN CHANGED");
        }else if(cityIndex.equals("second")){
            editor.putString(cityIndex,cityToken);
            editor.apply();
            editor.commit();
            Log.i("geturl","THE scd CITY HAS BEEN CHANGED");
        }else if (cityIndex.equals("third")){
            editor.putString(cityIndex,cityToken);
            editor.apply();
            editor.commit();
            Log.i("geturl","THE trd CITY HAS BEEN CHANGED");
        }else{Log.i("geturl","THE CITY HAS NOT BEEN CHANGED");}
    }
}