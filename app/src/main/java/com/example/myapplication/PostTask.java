package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PostTask extends AsyncTask<String, Long, String> {


    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("http://113.190.232.235:30100/idocNet.Test.Logistic.WMS/Services/Login");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");



            // khoi tao param
            StringBuilder params = new StringBuilder();

            params.append("&");
            params.append("username=wms.csc");
            params.append("&");
            params.append("password=0StFbim5pSrmBncQU0RpnA");

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter(os, "UTF-8"));

            // dua param vao body cua request
            writer.append(params);

            // giai phong bo nho
            writer.flush();
            // ket thuc truyen du lieu vao output
            writer.close();
            os.close();


            // lay du lieu tra ve
            StringBuilder response = new StringBuilder();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }


            return response.toString();

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
        Log.e("data", s);

    }
}
