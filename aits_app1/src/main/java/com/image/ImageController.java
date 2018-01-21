package com.image;

import com.image.base.BaseRequest;
import com.image.base.BaseResponse;
import com.image.utils.HandlePic;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class ImageController {

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/img/render"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    //支持跨域
    @CrossOrigin(origins = "*")
    @ResponseBody
    public String execute(BaseRequest request,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        //测试封装方法
        System.out.println(request.returnType()+"----");
        httpServletRequest.getParameter("outType");
        //img为图片的二进制流
        //项目里的相对路径需要从src往下找路径
        byte[] img = HandlePic.image2byte("src/main/java/com/image/pic/test.png");
       //设置content-type方便对方解析
        httpServletResponse.setContentType("image/png");
        //字节流输出有别于Writer字符流出书因为不用考虑编码所以不适用字符流，字节流默认不支持缓存
        OutputStream os = httpServletResponse.getOutputStream();
        os.write(img);
        os.flush();
        os.close();
        return "success";
    }

    protected void buildResponse(BaseRequest request, BaseResponse response,byte[] bytes){
        switch (request.returnType()){
            case URL:
                System.out.println("A");
            case BASE64:
                System.out.println("Base64");
            case STREAM:
                System.out.println("Steam");
        }
    }



}
