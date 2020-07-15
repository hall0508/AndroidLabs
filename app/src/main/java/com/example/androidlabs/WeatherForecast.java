package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {
    ImageView image;
    TextView currentTemp;
    TextView minTemp;
    TextView maxTemp;
    TextView UVRating;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        image = findViewById(R.id.weatherImage);
        currentTemp = findViewById(R.id.currentTemp);
        minTemp = findViewById(R.id.minTemp);
        maxTemp = findViewById(R.id.maxTemp);
        UVRating = findViewById(R.id.UVRating);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        ForecastQuery req = new ForecastQuery();
        req.execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric");
    }
    class ForecastQuery extends AsyncTask<String,Integer,String> {
        //String UV,min,max,currentTemp;
        Bitmap picture;
        @Override
        protected String doInBackground(String... strings) {
            //create a URL object of what server to contact:
            URL url = null;
            try {
                url = new URL(strings[0]);
            //open the connection
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            //wait for data:
                InputStream response = urlConnection.getInputStream();


                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput( response  , "UTF-8");
                String parameter = null;
                int eventType = xpp.getEventType(); //The parser is currently at START_DOCUMENT
                while (eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG)
                    {
                        if(xpp.getName().equals("temperature")){
                            currentTemp.setText(xpp.getAttributeValue(null,"value").toString());
                            minTemp.setText(xpp.getAttributeValue(null,"min").toString());
                            maxTemp.setText(xpp.getAttributeValue(null,"max").toString());
                        }
                    }
                    eventType = xpp.next();
                }
                publishProgress(25,50,75);
            } catch (Exception e) {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}