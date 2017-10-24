package com.aits.controller;

import com.alibaba.fastjson.JSONObject;
import com.aits.entity.QueryLine;
import com.aits.service.CityCodeService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jared
 */
@Controller
@RequestMapping("/ticketInfo")
public class TrainTicketController {
    @Autowired
    public CityCodeService cityCodeService;

    @RequestMapping("/getAllCity")
    public @ResponseBody
    List<Map<String, Object>> getAllCity() {
        List<Map<String, Object>> list = cityCodeService.getAllCity();
        return list;
    }

    public static HttpClient getHttpClient() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        //增加ssl校验
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, new TrustManager[]
                {new TrustAnyTrustManager()}, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
        return httpClient;
    }

    @RequestMapping("/getTicket")
    public @ResponseBody
    List<QueryLine> getTicket(String train_date,String from_station,String to_station,String purpose_codes) throws ClientProtocolException, IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        HttpClient httpClient = TrainTicketController.getHttpClient();
        HttpGet get = new HttpGet("https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date="+train_date+"&leftTicketDTO.from_station="+from_station+"&leftTicketDTO.to_station="+to_station+"&purpose_codes="+purpose_codes+"");
        HttpResponse httpResponse = httpClient.execute(get);
        HttpEntity entity = httpResponse.getEntity();

        String body = EntityUtils.toString(entity);
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String result=JSONObject.parseObject(jsonObject.get("data").toString()).get("result").toString();
        List resultlist =  JSONObject.parseArray(result);
        List<QueryLine> list=new ArrayList<>();
        for (int i = 0; i < resultlist.size(); i++) {
            String dd[]=resultlist.get(i).toString().split("\\|");
            QueryLine queryLine=new QueryLine();

            String trainNumber= dd[3];
            queryLine.setTrainNumber(trainNumber);
            queryLine.setDepartureStation(dd[4]);
            queryLine.setDestinationStation(dd[5]);
            queryLine.setInitialTime(dd[8]);
            queryLine.setArriveTime(dd[9]);
            queryLine.setLast(dd[10]);

            queryLine.setSoftSleeper(dd[23]);
            queryLine.setHardSleeper(dd[26]);
            queryLine.setHardSeat(dd[28]);
            queryLine.setNoSeat(dd[29]);

            queryLine.setBusinessSeat(dd[30]);
            queryLine.setFirstSeat(dd[31]);
            queryLine.setSecondSeat(dd[32]);
            list.add(queryLine);
        }
        String json=  JSONObject.toJSONString(list);
        System.out.println(json);

        return list;
    }

    public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        TrainTicketController yp = new TrainTicketController();
      //  yp.getTicket("2017-10-20","BJP","XMS","ADULT");
        yp.getAllCity();
    }


    @RequestMapping("getIcon.jspx")
    public void getIcon() throws Exception, IOException {
        HttpClient httpClient = TrainTicketController.getHttpClient();
        String urlString = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&" + Math.random();
        HttpGet httpGet = new HttpGet(urlString);
        //发送请求
        HttpResponse response = httpClient.execute(httpGet);

        //输出返回值
        InputStream is = response.getEntity().getContent();
        BufferedInputStream in = new BufferedInputStream(is);

        //保存的图片文件名
        File img = new File("E:/image/title.png");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(img));

        byte[] buf = new byte[1];
        while (in.read(buf) != -1) {
            out.write(buf);
        }
        in.close();
        out.close();

        //----------------------

        //在当前项目中生成文件--
        String savePath = "";
        java.net.URL url = TrainTicketController.class.getClassLoader().getResource("/");
        savePath = url.toString();

        int start = savePath.indexOf("/");
        int end = savePath.indexOf("/WEB-INF");
        savePath = savePath.substring(start, end);


        //创建一个图片缓冲区
        BufferedImage im = ImageIO.read(img);
        //new  BufferedImage(293,190,BufferedImage.TYPE_INT_BGR);
        //获取图片缓冲区
        Graphics graphics = im.getGraphics();

        BufferedImage title = im.getSubimage(0, 0, 293, 30);

        File file = new File("E:/image/title.png");

        ImageIO.write(title, "PNG", file);

        BufferedImage body = null;
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                body = im.getSubimage(73 * i, 30, 73, 80);
            }
            if (i >= 4) {
                body = im.getSubimage(73 * (i - 4), 110, 73, 80);
            }
            File f = new File(savePath + "/yanzheng" + i + ".png");

            ImageIO.write(body, "PNG", f);

        }

        //---------------------

    }

    //获得百度识图后的数据
    @RequestMapping("getIconText.jspx")
    public void getIconText() throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, ClientProtocolException, IOException {
        HttpClient httpClient = TrainTicketController.getHttpClient();
        for (int i = 0; i < 8; i++) {
            String img = "http://liu54673.xicp.net/liu_blog/yanzheng" + i;
            img = URLEncoder.encode(img, "GBK");
            String urlString = "http://stu.baidu.com/n/pc_search?rn=10&appid=0&tag=1&isMobile=0&queryImageUrl=" + img + ".png&querySign=&fromProduct=&productBackUrl=&fm=&uptype=plug_in";
            HttpGet httpGet = new HttpGet(urlString);
            //发送请求
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            //输出返回值
            String html = EntityUtils.toString(entity);

            Document document = Jsoup.parse(html);

            System.out.println("");
            System.out.println("第" + (i + 1) + "个图可能是：");
            Elements eles = document.getElementsByClass("guess-info-word-link");
            for (int j = 0; j < eles.size(); j++) {
                System.out.print(eles.get(j).text() + "  ");
            }
        }

        System.out.println("");

    }


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


}
