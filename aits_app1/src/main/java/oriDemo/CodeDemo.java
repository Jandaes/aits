package oriDemo;

import sun.nio.cs.UnicodeEncoder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
       String s;
       s="a";
       int h=0;
       char[] c= s.toCharArray();

     // byte[] b= s.getBytes("utf-8");
        for (int i = 0; i < c.length; i++) {
            h = 31 * h + c[i];
        }
        System.out.println(h);
        System.out.println(s.hashCode());



        char ch='我';
    }
}
