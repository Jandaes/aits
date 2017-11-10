package com.aits.core.arithmetic.compress;

public class Test {
    public static void main(String[] args) throws Exception {
            //创建压缩对象
            Compress compress = new Compress();
            //统计文件中0-255出现的次数
            compress.countTimes("C:\\Users\\Administrator\\Desktop\\11.bmp");
            //构造哈夫曼树，并得到根节点
            HuffmNode root=compress.createTree();
            //得到哈夫曼编码
            compress.getHuffmCode(root, "");
            //压缩文件
            compress.compress("C:\\Users\\Administrator\\Desktop\\11.bmp",
                    "C:\\Users\\Administrator\\Desktop\\my.zip");
            
    }
}
