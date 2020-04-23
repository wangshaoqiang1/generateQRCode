package com.feiwangSpring.service.impl;

import com.alibaba.druid.util.Base64;
import com.feiwangSpring.service.GenerateCodeService;
import com.generate.CreateAuthQRCode;
import com.generate.GenerateCode_wsq;
import com.generate.GeneratorForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/17 13:39
 */
@Component
@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {
    @Value("${filegeneratepath}")
    private String filegeneratepath;
    private static final Logger logger = LoggerFactory.getLogger(GenerateCodeServiceImpl.class);
    @Override
    public Map generateCodeByContent(String Content, Integer size,Integer color,String type) {
        if ("png".equals(type) || "jpg".equals(type)|| "bmp".equals(type)|| "gif".equals(type)) {
            return generImage(Content, size, color, type);
        }
      else   if ("svg".equals(type) || "pdf".equals(type) || "eps".equals(type)) {
       return      generFile(Content, size, color, type);


        }
        else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("code", "1");
            map.put("Base64Result", "不支持格式");
            map.put("type", type);
            return  map;
        }
    }
    /**
     * create by: wsq
     * description: TODO 入托type是png jpg 则生成图片
     * create time: 2020/4/23 11:08
     *
     * @Param: Content
     * @Param: size
     * @Param: color
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    private Map<String, String>  generImage(String Content, Integer size, Integer color,String type){

        Map<String, String> map = new HashMap<String, String>();
        try {
            GenerateCode_wsq gen=new GenerateCode_wsq();
//            //0彩色 1黑白 2IDcode认证
            BufferedImage image= gen.generateCode(Content,size,color);
            //GeneratorForm wsq=new  GeneratorForm();
            //   BufferedImage image=   wsq.getQRimage_wsq(Content,size,color);
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            ImageIO.write(image, type, os);

            InputStream is = new ByteArrayInputStream(os.toByteArray());
            String result = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            //   String Base64Result=  getBase64FromInputStream(is);
            byte[] bytes = os.toByteArray();//转换成字节
            BASE64Encoder encoder = new BASE64Encoder();
            String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n

            map.put("code", "0");
            map.put("base64", png_base64);
            map.put("type", type);

        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.info("生成二维码错误："+ex.toString());
            map.put("code", "1");
            map.put("Base64Result", "");
            map.put("type", type);
        }


        return map;
    }
    public  Map generFile(String Content, Integer size, Integer color,String type){
        Map<String, String> map = new HashMap<String, String>();
try {
    GenerateCode_wsq gen=new GenerateCode_wsq();
    String path= gen.generateFile(Content,size,color,type,filegeneratepath);
    System.out.println(path);
    File file = new File(path);
    FileInputStream inputFile = new FileInputStream(file);
    byte[] buffer = new byte[(int) file.length()];
    inputFile.read(buffer);
    inputFile.close();
    String fileBase64= new BASE64Encoder().encode(buffer);
    map.put("code", "0");
    map.put("base64", fileBase64);
    map.put("type", type);
}
catch (Exception ex){
ex.printStackTrace();
    map.put("code", "1");
    map.put("base64", "");
    map.put("type", type);
}
return  map;

    }

    private String getBase64FromInputStream(InputStream is) throws Exception {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;

        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new Exception("输入流关闭异常");
                }
            }
        }

        return Base64.byteArrayToBase64(data);
    }


    private static byte[] imageToBytes(BufferedImage bImage) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "png", out);
        } catch (IOException e) {
            //log.error(e.getMessage());
        }
        return out.toByteArray();
    }
}
