package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    Bitmap picture = null;
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
        String UV,min,max,currentTemperature;

        @Override
        protected String doInBackground(String... strings) {
            //create a URL object of what server to contact:
            URL url = null;
            String iconName = null;
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
                            currentTemperature = xpp.getAttributeValue(null,"value").toString();
                            publishProgress(25,50,75);
                            min = xpp.getAttributeValue(null,"min").toString();
                            publishProgress(25,50,75);
                            max = xpp.getAttributeValue(null,"max").toString();
                            publishProgress(25,50,75);
                        }
                        if(xpp.getName().equals("weather")){
                             iconName = xpp.getAttributeValue(null,"icon").toString();
                        }

                        }

                    eventType = xpp.next();
                }

                String urlString = "http://openweathermap.org/img/w/" + iconName + ".png";
                if (fileExistance(iconName+".png")){
                    FileInputStream fis = null;
                    try {
                        fis = openFileInput(iconName+".png");
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    Bitmap bm = BitmapFactory.decodeStream(fis);
                    picture = bm;
                    Log.i("Bitmap","This bitmap was found!" + iconName+".png");
                    publishProgress(100);

                }else {
                    URL urlImage = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) urlImage.openConnection();
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        picture = BitmapFactory.decodeStream(connection.getInputStream());
                        publishProgress(100);
                    }
                    FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                    picture.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String link = "http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389";
            try {
                URL tempURL = new URL(link);
                HttpURLConnection tempUrlConnection = (HttpURLConnection) tempURL.openConnection();
                InputStream tempResponse = tempUrlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(tempResponse, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                String result = sb.toString();
                JSONObject uvReport = new JSONObject(result);
                double uvRating = uvReport.getDouble("value");
                Log.i("uv", "The uv is now: " + uvRating) ;
                UV= uvRating+"";
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public boolean fileExistance(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        @Override
        protected void onPostExecute(String s) {
            currentTemp.setText(currentTemperature);
            minTemp.setText(min);
            maxTemp.setText(max);
            image.setImageBitmap(picture);
            UVRating.setText(UV);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }
    }
}