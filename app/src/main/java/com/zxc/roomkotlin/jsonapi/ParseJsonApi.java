package com.zxc.roomkotlin.jsonapi;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParseJsonApi extends AsyncTask<String, Void, Object[]> {

    public interface Response {
        void onSuccess(String jsonString);

        void onError(String errorMessage);
    }

    private Response response;

    public ParseJsonApi(Response response) {
        this.response = response;
    }

    @Override
    protected Object[] doInBackground(String... args) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) (new URL(args[0])).openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.connect();

            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.write(args[1].getBytes());
            outputStream.flush();
            outputStream.close();

            if (urlConnection.getResponseCode() != 200) {
                return new Object[]{false, urlConnection.getResponseMessage()};
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String str;
            StringBuilder stringBuilder = new StringBuilder();

            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }

            bufferedReader.close();

            urlConnection.disconnect();

            return new Object[]{true, stringBuilder.toString().trim()};
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[]{false, e.getMessage()};
        }
    }

    @Override
    protected void onPostExecute(Object[] objects) {
        super.onPostExecute(objects);
        if ((boolean) objects[0]) {
            response.onSuccess((String) objects[1]);
        } else {
            response.onError((String) objects[1]);
        }
    }
}

/*
        HOW TO USE THIS PARSE JSON API CLASS :-

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("contact", "3423432432423");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new ParseJsonApi(new Response() {
            @Override
            public void onSuccess(String jsonString) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        }).execute(
                "URL",
                jsonObject.toString().trim()
        );
*/