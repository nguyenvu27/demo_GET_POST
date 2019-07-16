package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv) ;
    }


    public void get(View view) {
        GetTask getTask = new GetTask() ;
        getTask.execute("http://dotplays.com/android/bai1.php?food=today") ;
    }

    public void post(View view) {
        PostTask postTask = new PostTask();
        postTask.execute("http://113.190.232.235:30100/idocNet.Test.Logistic.WMS/Services/Login");
    }


    public class GetTask extends AsyncTask<String, Long, String> {



        @Override
        protected String doInBackground(String... strings) {


            try{
                URL url = new URL(strings[0]) ;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection() ;
                InputStream inputStream = httpURLConnection.getInputStream() ;
                Scanner scanner = new Scanner(inputStream) ;
                String data = "" ;
                while (scanner.hasNext()){
                    data = data + scanner.nextLine() ;
                }
                scanner.close();
                return data ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e("data",s) ;
            textView.setText(s);
        }
    }


}
