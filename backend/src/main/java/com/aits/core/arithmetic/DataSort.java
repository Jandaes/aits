package com.aits.core.arithmetic;

import java.io.UnsupportedEncodingException;

/**
 * @author jared
 */
public class DataSort {
    private static int[] a={2,1,5,4,8,3,2};

        public void sort1(){
            for (int i = 0; i <=a.length-1; i++) {
                int temp;
                for (int j = i; j <a.length-1; j++) {
                    if(a[i]>a[j+1]){
                        temp=a[i];
                        a[i]=a[j+1];
                        a[j+1]=temp;
                    }
                }

                System.out.print(a[i]+" ");
            }
        }

      public static void main(String[] args) throws UnsupportedEncodingException {
            String a="哈士奇";
          byte b[]= a.getBytes("gbk");
          for (int i = 0; i <b.length ; i++) {
              System.out.println(b[i]);

          }

          System.out.println("----------");
          String ab="哈";
          byte ba[]= ab.getBytes("gbk");
          for (int i = 0; i <ba.length ; i++) {
              System.out.println(ba[i]);

          }
          String s=new String(new byte[]{-71,-2},"gbk");
          System.out.println("s="+s);
      }
}
