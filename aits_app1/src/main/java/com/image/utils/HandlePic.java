package com.image.utils;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class HandlePic {
    /**
     * 图片转byte[]数组
     *
     * @param path
     * @return
     */
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            //找到文件转为图片流文件
            input = new FileImageInputStream(new File(path));
            //捕获内存缓冲区的数据，转换成字节数组
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            //字节流转字节数组
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * byte数组到图片
     * @param data
     * @param path
     */
    public void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) {
            return;
        }
        try {
            //文件输出流，指向一个文件
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            //将data字节数字输出至文件输出流
            imageOutput.write(data,0,data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in"+path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * byte数组转16进制字符串（暂时未使用）
     * @param data
     * @return
     */
    public String byte2string(byte[] data){
        if(data==null||data.length<=1){
            return "0x";
        }
        if(data.length>200000){
            return "0x";
        }
        StringBuffer sb=new StringBuffer();
        int buf[]=new int[data.length];
        //byte数组转化为十进制
        for (int k = 0; k < data.length; k++) {
            buf[k]=data[k]<0?(data[k]+256):(data[k]);
        }
        //十进制转化为十六进制
        for (int k = 0; k < buf.length; k++) {
            if(buf[k]<16){
                sb.append("0"+Integer.toHexString(buf[k]));
            }else {
                sb.append(Integer.toHexString(buf[k]));
            }
        }
        return "0x"+sb.toString().toUpperCase();


    }
}
