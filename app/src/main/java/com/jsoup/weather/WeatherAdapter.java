package com.jsoup.weather;

import android.app.VoiceInteractor;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {

    String Array[] = new String[15];
    String dayArray[];
    String degreeArray[];
    String forecastArray[];
    String moistureArray[];
    Context context;

    public WeatherAdapter(Context context, String[] dayArray, String[] degreeArray, String[] forecastArray, String[] moistureArray) {
        this.context = context;
        this.dayArray = dayArray;
        this.degreeArray = degreeArray;
        this.forecastArray = forecastArray;
        this.moistureArray = moistureArray;

    }

    @Override
    public int getCount() {
        return Array.length;
    }

    @Override
    public Object getItem(int i) {
        return Array[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_list, viewGroup, false);

        TextView dayView = view.findViewById(R.id.dayBox);
        TextView degreeView = view.findViewById(R.id.degreeBox);
        ImageView iconView = view.findViewById(R.id.iconBox);
        TextView rainView = view.findViewById(R.id.rainBox);

        String day = dayArray[i];
        String degree = degreeArray[i];
        String icontext = forecastArray[i];
        String rain = moistureArray[i];

        dayView.setText(day);
        degreeView.setText(degree);
//-------------------------------------------- TEST FOR FORECAST TITLE
        switch (icontext) {
            case "Açık":
                iconView.setImageResource(R.drawable.sunny);
                break;
            case "ÖÖ Bulutlar / ÖS Güneş":
                iconView.setImageResource(R.drawable.cloudysun);
                break;
            case "Hafif Yağmur":
                iconView.setImageResource(R.drawable.lowrain);
                break;
            case "Hafif Yağmurlu":
                iconView.setImageResource(R.drawable.lowrain);
                break;
            case "Sağanak yağışlı":
                iconView.setImageResource(R.drawable.heavyrain);
                break;
            case "Çok Bulutlu":
                iconView.setImageResource(R.drawable.heavycloudy);
                break;
            case "ÖÖ Yağmur Geçişleri":
                iconView.setImageResource(R.drawable.raintransitionsm);
                break;
                case "ÖÖ Bulutlar / ÖS Güneş / Rüzgâr":
                iconView.setImageResource(R.drawable.lowrainwind);
                break;
            case "Kara Dönen Yağmur":
                iconView.setImageResource(R.drawable.snowtransitions);
                break;
            case "Kar":
                iconView.setImageResource(R.drawable.snow);
                break;
            case "ÖS Yağmur Geçişleri":
                iconView.setImageResource(R.drawable.raintransitionsa);
                break;
            case "Yağmur Geçişleri":
                iconView.setImageResource(R.drawable.raintransitions);
                break;
            case "Yağmur":
                iconView.setImageResource(R.drawable.rainy);
                break;
            case "Çoğunlukla Güneşli":
                iconView.setImageResource(R.drawable.mostlysunny);
                break;
            case "Hafif Kar":
                iconView.setImageResource(R.drawable.snowtransitions);
                break;
            case "Güneşli":
                iconView.setImageResource(R.drawable.sunny);
                break;
            case "ÖÖ Hafif Yağmur":
                iconView.setImageResource(R.drawable.lowrainm);
                break;
            case "Erken Saatlerde Hafif Yağmur":
                iconView.setImageResource(R.drawable.lowrainm);
                break;
            case "Erken Saatlerde Yağmur":
                iconView.setImageResource(R.drawable.lowrainm);
                break;
            case "ÖS Hafif Yağmur":
                iconView.setImageResource(R.drawable.lowraina);
                break;
            case "Yağmur / Kar Geçişleri":
                iconView.setImageResource(R.drawable.rainsnowtransitions);
                break;
            case "Karla karışık yağmurlu":
                iconView.setImageResource(R.drawable.rainsnow);
                break;
            case "Kar Geçişi":
                iconView.setImageResource(R.drawable.snowtransitions);
                break;
            case "Parçalı Bulutlu":
                iconView.setImageResource(R.drawable.cloudysun);
                break;
            case "ÖÖ Yağmur / Kar":
                iconView.setImageResource(R.drawable.rainsnowm);
                break;
            case "ÖS Yağmur / Kar":
                iconView.setImageResource(R.drawable.rainsnowa);
                break;
            case "ÖS Yağmur / Buz":
                iconView.setImageResource(R.drawable.rainicem);
                break;
            case "ÖÖ Yağmur / Buz":
                iconView.setImageResource(R.drawable.rainicea);
                break;
            case "ÖS Kar Geçişleri":
                iconView.setImageResource(R.drawable.snowtransitionsm);
                break;
            case "ÖÖ Kar Geçişleri":
                iconView.setImageResource(R.drawable.snowtransitionsa);
                break;
            case "Çoğunlukla Bulutlu":
                iconView.setImageResource(R.drawable.mostlycloudy);
                break;
            case "Hafif Yağmur / Rüzgâr":
                iconView.setImageResource(R.drawable.lowrainwind);
                break;
            case "İlerleyen Saatlerde Yağmur Geçişleri":
                iconView.setImageResource(R.drawable.lowrain);
                break;
            case "Yağmur / Donuk Yağmur":
                iconView.setImageResource(R.drawable.rainicea);
                break;
            case "Yağmura Dönen Buz":
                iconView.setImageResource(R.drawable.rainicea);
                break;
            case "Yağmura Dönen Kar":
                iconView.setImageResource(R.drawable.rainsnow);
                break;
            case "İlerleyen Saatlerde Kar Geçişleri":
                iconView.setImageResource(R.drawable.snowtransitions);
                break;
            case "Bulutlu":
                iconView.setImageResource(R.drawable.heavycloudy);
                break;
        }
//-----------------------------------------------------------------------
        rainView.setText(rain);

        return view;
    }


}
