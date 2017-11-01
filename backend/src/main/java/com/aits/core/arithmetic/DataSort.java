package com.aits.core.arithmetic;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;

/**
 * @author jared
 */
public class DataSort {
    private static int[] a = {2, 1, 5, 4, 8, 3, 2};

    /**
     * 冒泡排序
     */
    public void sort1() {
        for (int i = 0; i <= a.length - 1; i++) {
            int temp;
            for (int j = i; j < a.length - 1; j++) {
                if (a[i] > a[j + 1]) {
                    temp = a[i];
                    a[i] = a[j + 1];
                    a[j + 1] = temp;
                }
            }

            System.out.print(a[i] + " ");
        }
    }

    /**
     * utf8汉字占3字节，gbk占2个字节
     *
     * @throws UnsupportedEncodingException
     */
    public void showByte() throws UnsupportedEncodingException {
        String a = "哈士奇";
        byte b[] = a.getBytes("gbk");
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);

        }

        System.out.println("----------");
        String ab = "哈";
        byte ba[] = ab.getBytes("gbk");
        for (int i = 0; i < ba.length; i++) {
            System.out.println(ba[i]);

        }
        String s = new String(new byte[]{-71, -2}, "gbk");
        System.out.println("s=" + s);
    }

    public static void main(String[] args) throws IOException {
//        FileImageInputStream fis=new FileImageInputStream(new File("C:\\Users\\Administrator\\Desktop\\aa.png"));
//        ByteArrayOutputStream out=new ByteArrayOutputStream();
//        byte[] buf=new byte[1024];
//        int readNum=0;
//        byte[] data;
//        while((readNum=fis.read(buf))!=-1){
//            out.write(buf,0,readNum);
//        }
//        data=out.toByteArray();
//        for (int i = 0; i <data.length ; i++) {
//            if(data[i]!=0){
//                System.out.print(data[i]+" ");
//            }
//        }
       byte[] b= DataSort.image2byte("C:\\\\Users\\\\Administrator\\\\Desktop\\\\aa.png");



        System.out.println("该图片二进制为:");
        for (int i = 0; i <b.length ; i++) {
         //   System.out.print(b[i]+" ");
            Integer in=new Integer(b[i]);
            String s=Integer.toBinaryString(in);
          // System.out.println();
            System.out.print(in+" ");
        }
       // System.out.println(DataSort.returnActualLength(data));

    }
    public static byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }


    public static int returnActualLength(byte[] data) {
        int i = 0;
        for (; i < data.length; i++) {
            if (data[i] == '\0'){
                break;
            }
        }
        return i;
    }
}
