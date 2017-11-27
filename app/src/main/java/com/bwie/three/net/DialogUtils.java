package com.bwie.three.net;
import android.annotation.SuppressLint;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;


public class DialogUtils {
        public static long MAX_SIZE = 0;
        public static long PROGRESS = -2;
        public static void showUpdataDialog(final Context context,String mp4Url){
            new DownloadThread(mp4Url, context.getCacheDir() + "/xia.mp4").start();
            showProgress(context);
        }
        @SuppressLint("StaticFieldLeak")
        /**
         * 开始下载
         */
        public static void showProgress(final Context context){
            final ProgressDialog pd = new ProgressDialog(context);
            pd.setTitle("正在下载");
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setMax(100);
            pd.show();
            new AsyncTask<String, Integer, String>(){
                @Override
                protected String doInBackground(String... strings) {
                    while (PROGRESS + 1 < MAX_SIZE){
                        SystemClock.sleep(100);
                        if(MAX_SIZE > 0){
                            publishProgress((int)(PROGRESS * 100 / MAX_SIZE));
                        }
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    pd.dismiss();
                }
                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    pd.setProgress(values[0]);
                }
            }.execute();
        }
    }
