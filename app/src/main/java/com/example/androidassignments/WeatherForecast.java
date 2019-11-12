package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WeatherForecast extends AppCompatActivity{
    private final String ACTIVITY_NAME = "WeatherForecastActivity";
    String city = StartActivity.selected_city;
    String WEATHER_API = "https://api.openweathermap.org/data/2.5/weather?q=" + city + ",ca&APPID=165396e29a5ecea9830b1b34ad4e5109&mode=xml&units=metric";

    ProgressBar progressBar;

    ImageView imageView;
    TextView current_temp;
    TextView min_temp;
    TextView max_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        Log.i(ACTIVITY_NAME,city);

        current_temp = findViewById(R.id.current_temp);
        min_temp = findViewById(R.id.min_temp);
        max_temp = findViewById(R.id.max_temp);
        imageView = findViewById(R.id.image_forecast);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        ForecastQuery f = new ForecastQuery();
        f.execute();
    }



    private class ForecastQuery extends AsyncTask<String, Integer, String> {
        private String currentTemp;
        private String minTemp;
        private String maxTemp;
        private Bitmap picture;

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(WEATHER_API);

                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                InputStream in = conn.getInputStream();

                try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(in, null);

                    //While you're not at the end of the document:
                    int type;
                    while((type = parser.getEventType()) != XmlPullParser.END_DOCUMENT) {
                        Log.i(ACTIVITY_NAME, "Checking");
                        //Are you currently at a Start Tag?
                        if(parser.getEventType() == XmlPullParser.START_TAG) {
                            if(parser.getName().equals("temperature") ) {
                                currentTemp = parser.getAttributeValue(null, "value");
                                publishProgress(25);
                                minTemp = parser.getAttributeValue(null, "min");
                                publishProgress(50);
                                maxTemp = parser.getAttributeValue(null, "max");
                                publishProgress(75);
                            }
                            else if (parser.getName().equals("weather")) {
                                String iconName = parser.getAttributeValue(null, "icon");
                                String fileName = iconName + ".png";

                                Log.i(ACTIVITY_NAME,"Looking for file: " + fileName);
                                if (fileExistance(fileName)) {
                                    FileInputStream fis = null;
                                    try {
                                        fis = openFileInput(fileName);

                                    }
                                    catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    Log.i(ACTIVITY_NAME,"Found the file locally");
                                    picture = BitmapFactory.decodeStream(fis);
                                }
                                else {
                                    String iconUrl = "https://openweathermap.org/img/w/" + fileName;
                                    picture = getImage(new URL(iconUrl));

                                    FileOutputStream outputStream = openFileOutput( fileName, Context.MODE_PRIVATE);
                                    picture.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                    Log.i(ACTIVITY_NAME,"Downloaded the file from the Internet");
                                    outputStream.flush();
                                    outputStream.close();
                                }
                                publishProgress(100);
                            }
                        }
                        // Go to the next XML event
                        parser.next();
                    }
                } finally {
                    in.close();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return "";

        }

        public boolean fileExistance(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        public Bitmap getImage(URL url) {
            HttpsURLConnection connection = null;
            try {
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    return BitmapFactory.decodeStream(connection.getInputStream());
                } else
                    return null;
            } catch (Exception e) {
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String a) {
            progressBar.setVisibility(View.INVISIBLE);
            imageView.setImageBitmap(picture);
            current_temp.setText(currentTemp + "C\u00b0");
            min_temp.setText(minTemp + "C\u00b0");
            max_temp.setText(maxTemp + "C\u00b0");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

    }
}
