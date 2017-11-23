package com.msb_demo1.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 所有自定义Controller的顶级接口,封装常用的与session和response、request相关的操作
 * @author haodaquan
 * @create 2017-11-22 14:36
 **/
public class BaseController {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
            .create();
    protected JsonResult result;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 客户端返回JSON字符串
     * @param response
     * @param object
     * @return
     */
    protected String renderSuccessString(HttpServletResponse response, Object object,String message) {
        result = new JsonResult();
        result.setStatus(200);
        result.setData(object);
        result.setMessage("操作成功");
        if(!"".equals((message == null) ? "" : message)){
            result.setMessage(message);
        }
        return renderString(response,gson.toJson(result),"application/json");
    }
    protected String renderErrorString(HttpServletResponse response,String reason) {
        result = new JsonResult();
        result.setStatus(300);
        result.setData(null);
        result.setMessage(reason);
        return renderString(response,gson.toJson(result),"application/json");
    }

    /**
     * 列表页专用返回Json字符串
     * @param response
     * @param object
     * @param count
     * @return
     */
    protected String renderPageSuccessString(HttpServletResponse response,Object object,long count) {
        PageResult result = new PageResult();
        result.setCount(count);
        result.setData(object);
        result.setCode(0);
        return renderString(response,gson.toJson(result),"application/json");
    }

    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, gson.toJson(object),"application/json");
    }

    /**
     * 客户端返回字符串
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string,String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.getWriter().write((string));
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 生成文件夹，返回文件相对地址
     * @param imgRootPath 文件根目录
     * @param filePath 文件项目目录
     * @return /filename/2017-11-21
     */
    protected String mkFilePath(String imgRootPath,String filePath){
        //当前日期
        Date date = new Date();
        //格式化并转换String类型
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String path = imgRootPath + "/" + filePath + "/" + datePath;

        //创建文件夹
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        return "/" + filePath + "/" + datePath;
    }

    /**
     *
     * @param file 提交的文件对象
     * @param imgPath 二级或多级目录，前后不需要出现/
     * @return
     * @throws IOException
     */
    protected String UploadImage(MultipartFile file, String imgPath, Integer imgWidth, Integer imgHeight) throws IOException {
        //获取物理路径webapp所在路径
        WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext
                .getServletContext();
        // 得到文件绝对路径
        String pathRoot = servletContext.getRealPath("/Uploads");

        List<String> exts = Arrays.asList( "jpg","png","gif","bmp","jpeg");

        if (file.isEmpty()){
            return "ERROR:file not exist！";
        }

        //判断是否要求图片尺寸
        if(imgHeight!=0 && imgWidth!=0){
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image!=null){
                if(image.getWidth() != imgWidth || image.getHeight() != imgHeight){
                    return "ERROR: Width and height is not matched";
                }
            }else{
                return "ERROR: no image";
            }
        }
        //获得文件类型（可以判断如果不是图片，禁止上传）
        String contentType=file.getContentType();
        //获得文件后缀名称
        String imageName=contentType.substring(contentType.indexOf("/")+1);
        if (exts.contains(imageName.toLowerCase()) == false){
            return "ERROR: illegal type";
        }

        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        String imgRealPath = mkFilePath(pathRoot,imgPath);
        String path = imgRealPath +"/"+uuid+"."+imageName;
        file.transferTo(new File(pathRoot + path));
        //组装http地址
        String imgUrl = "/Uploads" + path;
        return imgUrl;
    }

    /**
     * Md5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**判断用户密码是否正确
     * @param newpasswd  用户输入的密码
     * @param oldpasswd  数据库中存储的密码－－用户密码的摘要
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public boolean CheckPassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{

        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }

//    public boolean SetSession(String )

}
