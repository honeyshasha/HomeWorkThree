package com.bwie.three.net;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
public class NetUtils {
    public static void downloadFile(String downloadUrl, String path, int blockSize, int startPosition){
        BufferedInputStream bis=null;
        RandomAccessFile raf=null;
        try {
            File file=new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
            URL url=new URL(downloadUrl);
            Log.i("===download===", "downloadFile: "+downloadUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(8000);
            urlConnection.setConnectTimeout(8000);
            if(blockSize>0){
                long end = blockSize + startPosition - 1;
                urlConnection.setRequestProperty("Range","bytes="+startPosition+"-"+end);
                Log.i(Thread.currentThread() + "======", "bytes====" + startPosition + "-" + end);
            }
            int code=urlConnection.getResponseCode();
            if(code<400){
                bis=new BufferedInputStream(urlConnection.getInputStream());
                raf=new RandomAccessFile(file,"rwd");
                raf.seek(startPosition);
                int len=0;
                byte[] buff=new byte[1024*8];
                while ((len=bis.read(buff))!=-1){
                    synchronized (NetUtils.class){
                        raf.write(buff,0,len);
                        if(DialogUtils.PROGRESS<0){
                            DialogUtils.PROGRESS=0;
                        }
                        DialogUtils.PROGRESS+=len;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf!=null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static int getFileLength(String downloadUrl){
        int len=0;
        try {
            URL url=new URL(downloadUrl);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            len=connection.getContentLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }
}
