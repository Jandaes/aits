package com.aits.utils;

import com.google.common.base.Strings;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Jared
 * 验证码生成
 */
@WebServlet(name = "authCodeServlet", urlPatterns = "/authCode")
public class AuthCodeServlet extends HttpServlet {

    /**
     * 默认验证码图片的宽度
     */
    private static final int DEFAULT_WIDTH = 80;
    /**
     * 默认验证码图片的高度
     */
    private static final int DEFAULT_HEIGHT = 28;
    /**
     * 默认验证码字符个数
     */
    private static final int DEFAULT_CODE_COUNT = 4;
    /**
     * 默认随机干扰线条数
     */
    private static final int DEFAULT_LINE_COUNT = 15;
    /**
     * 默认随机干扰点数
     */
    private static final int DEFAULT_POINT_COUNT = 30;
    /**
     * 验证码字符生成范围
     */
    private static final String RANDOM_STRING = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String RANDOM_NUMBER = "0123456789";
    /**
     * 备选字体
     */
    private static final String[] FONT_TYPES = {"Tahoma", "Times New Roman", "Franklin Gothic Medium"};
    /**
     * 备选字体个数
     */
    private static final int FONT_TYPES_LEN = FONT_TYPES.length;
    /**
     * 验证码图片的宽度
     */
    private int width;
    /**
     * 验证码图片的高度
     */
    private int height;
    /**
     * 验证码字符个数
     */
    private int codeCount;
    /**
     * 随机干扰线条数
     */
    private int lineCount;
    /**
     *  随机干扰点数
     */
    private int pointCount;
    private String randomString = RANDOM_STRING;
    private int randomStringLength = RANDOM_STRING.length();
    private Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(response);
    }

    @Override
    public void init() throws ServletException {
        System.setProperty("java.awt.headless", "true");
        super.init();
        width = Strings.isNullOrEmpty(getInitParameter("width")) ? DEFAULT_WIDTH : Integer.parseInt(getInitParameter("width"));
        height = Strings.isNullOrEmpty(getInitParameter("height")) ? DEFAULT_HEIGHT : Integer.parseInt(getInitParameter("height"));
        codeCount = Strings.isNullOrEmpty(getInitParameter("codeCount")) ? DEFAULT_CODE_COUNT : Integer.parseInt(getInitParameter("codeCount"));
        lineCount = Strings.isNullOrEmpty(getInitParameter("lineCount")) ? DEFAULT_LINE_COUNT : Integer.parseInt(getInitParameter("lineCount"));
        pointCount = Strings.isNullOrEmpty(getInitParameter("pointCount")) ? DEFAULT_POINT_COUNT : Integer.parseInt(getInitParameter("pointCount"));
        randomString = "number".equalsIgnoreCase(getInitParameter("type")) ? RANDOM_NUMBER : RANDOM_STRING;
        randomStringLength = randomString.length();
    }

    private void process(HttpServletResponse response) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.fillRect(0, 0, width, height);// 设定边框
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18)); // 设定字体

        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < lineCount; i++) {
            g.setColor(getRandColor(111, 133));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(13);
            int yl = random.nextInt(15);
            g.drawLine(x, y, x + xl, y + yl);
        }

        for (int i = 0; i < pointCount; i++) {
            g.setColor(getRandColor(130, 150));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 1, 1);
        }

        StringBuilder buffer;
        int xoffset = width / codeCount;
        buffer = new StringBuilder();
        for (int i = 0; i < codeCount; i++) {
            g.setFont(getsFont());
            g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
            char rand = getRandomString();
            buffer.append(rand);
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(String.valueOf(rand), xoffset * i, 16);
        }

        //验证码放置
        UserContextUtil.setAttribute(UserContextUtil.AUTH_CODE_KEY, buffer.toString());
        g.dispose();

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("P3P", "CP=CAO PSA OUR");
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    private Font getsFont() {
        return new Font(FONT_TYPES[random.nextInt(FONT_TYPES_LEN)], Font.BOLD, 18 + random.nextInt(2));
    }

    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 6);
        int g = fc + random.nextInt(bc - fc - 4);
        int b = fc + random.nextInt(bc - fc - 8);
        return new Color(r, g, b);
    }

    private char getRandomString() {
        return randomString.charAt(random.nextInt(randomStringLength));
    }
}
