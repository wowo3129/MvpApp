package com.flavors.xiaomi;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    public MyAsyncTask() {
        super();
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

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public static void main(String args[]){
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }
}
