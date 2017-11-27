package com.bwie.three.net;
public class DownloadTask extends Thread{
    String downloadUrl;
    String path;
    int blockSize;
    int startPosition;
    public DownloadTask(String downloadUrl, String path, int blockSize, int startPosition) {
        this.downloadUrl = downloadUrl;
        this.path = path;
        this.blockSize = blockSize;
        this.startPosition = startPosition;
    }
    @Override
    public void run() {
        NetUtils.downloadFile(downloadUrl,path,blockSize,startPosition);
    }
}
