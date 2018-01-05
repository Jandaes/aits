package com.fastjsonCopy.asm;

/**
 * @author jared
 */
public class ByteVector {
    byte[] data;
    int length;

    public ByteVector() {
        data = new byte[64];
    }

    public ByteVector(final int initialSize) {
        data = new byte[initialSize];
    }

    public ByteVector putByte(final int b) {
        int length = this.length;
        if (length + 1 > data.length) {

        }
        return this;
    }


    public ByteVector putUTF8(final String s){
        int charLength=s.length();
        int len=length;
        if(len+2+charLength>data.length){
            enlarge(2+charLength);
        }
        byte[] data=this.data;

        data[len++] = (byte) (charLength>>>8);
        data[len++] = (byte)charLength;

        for (int i = 0; i < charLength; ++i) {
            char c=s.charAt(i);
            if(c>='\001'&&c<='\177'){
                data[len++]=(byte)c;
            }
        }
        length=len;
        return this;
    }


    public static void main(String[] args) {
        String s="你好，世界";
        System.out.println(s.length());
        System.out.println((byte)(s.length()>>>8));
        for (int i = 0; i < s.length() ; i++) {
            char c=s.charAt(i);
            System.out.println(c);
        }
        char a='\001';
        System.out.println("---------"+a);

    }

    public ByteVector putByteArray(final byte[] b,final int off,final int len){
        if(length + len >data.length){
            enlarge(len);
        }
        if(b !=null){
            System.arraycopy(b,off,data,length,len);
        }
        length+=len;
        return this;
    }


    /**
     * 为数组扩容
     * @param size
     */
    private void enlarge(final int size) {
        int length1 = 2 * data.length;
        int length2 = length + size;
        byte[] newData = new byte[length1 > length2 ? length1 : length2];
        //实现数组之间的复制
        System.arraycopy(data, 0, newData, 0, length);
        data = newData;
    }



}
