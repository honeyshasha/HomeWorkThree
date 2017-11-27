package com.bwie.three.net;
public class DownloadThread extends Thread{
    private String downloadUrl;
    private String path;
    private int threadNum=5;

    public DownloadThread(String downloadUrl, String path) {
        this.downloadUrl = downloadUrl;
        this.path = path;
    }

    @Override
    public void run() {
        super.run();
        int len=NetUtils.getFileLength(downloadUrl);
        DialogUtils.MAX_SIZE = len;
        //例如  1000kb  3   333
        int blockSize = len/threadNum;
//        //四舍五入---  让一个数字+0。5在四舍五入 就变成 向上取整
//        int blockSize = (int) Math.round(tempSize + 0.5);
        //计算出下载块以后   创建线程执行下载操作
        for (int i = 0; i < threadNum; i++) {
            //计算开始位置
            int startPosition = blockSize * i;
            //让最后一个线程下载的大小是正好的，  总长度 - 除了最后一个块的大小和
            if(i == threadNum - 1){
                blockSize = len - blockSize * (threadNum - 1);
            }
            new DownloadTask(downloadUrl, path, blockSize, startPosition).start();
        }
    }
    public void setThreadNum(int threadNum){
        this.threadNum = threadNum;
    }
}
