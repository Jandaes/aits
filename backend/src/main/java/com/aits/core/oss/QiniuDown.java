package com.aits.core.oss;

import com.qiniu.util.Auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QiniuDown {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String fileName = "lsGomzvVmUkCXr2S4_SxcKnh6v5e";
        String domainOfBucket = "http://oysq1myjc.bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

        String accessKey = "pZgq-AFCS_lnHD0JmgUOISpTDWIfXa6lLBw2LD3N";
        String secretKey = "B7UHwIhHysgRlfVNISjVX7ViGOQ5UrjCSdrvvbwK";

        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);

        System.out.println(finalUrl);

    }
}
