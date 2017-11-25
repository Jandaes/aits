package com.aits.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

public class GetWork {

    // 自定义私有类
    private static class TrustAnyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

    }


    public static HttpClient getHttpClient() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        //增加ssl校验
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, new TrustManager[]
                {new GetWork.TrustAnyTrustManager()}, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
        return httpClient;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        CookieStore cookieStore = new BasicCookieStore();
          HttpClient httpClient  = GetWork.getHttpClient();
        try {
            HttpPost post = new HttpPost("https://www.lagou.com/jobs/positionAjax.json?city=%E4%B8%8A%E6%B5%B7&needAddtionalResult=false&isSchoolJob=0");
            post.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3159.5 Safari/537.36");
            post.setHeader("Cookie","user_trace_token=20171125175348-8873a53f-d1c6-11e7-a870-525400f775ce; LGUID=20171125175348-8873a92f-d1c6-11e7-a870-525400f775ce; index_location_city=%E4%B8%8A%E6%B5%B7; JSESSIONID=ABAAABAACEBACDG0FA9345BB516E3348D1B710658B769F4; _gid=GA1.2.135303804.1511603604; _ga=GA1.2.1452740841.1511603604; LGSID=20171125175348-8873a738-d1c6-11e7-a870-525400f775ce; LGRID=20171125183726-a15c93e1-d1cc-11e7-9a46-5254005c3644; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511603604,1511606223; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511606223; SEARCH_ID=e950406373804a31b35bd29b4f2933f2");
            post.setHeader("Referer","https://www.lagou.com/jobs/list_Java?city=%E4%B8%8A%E6%B5%B7&cl=false&fromSearch=true&labelWords=&suginput=");



            HttpResponse response = httpClient.execute(post);//
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为string类型
                String body = EntityUtils.toString(entity);
                System.out.println("body:" + body);
            }
            EntityUtils.consume(entity);
            // 释放链接
            // response.close();

            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("Local cookie: " + cookies.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
