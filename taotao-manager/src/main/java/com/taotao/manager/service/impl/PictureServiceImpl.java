package com.taotao.manager.service.impl;

import com.taotao.manager.service.PictureSerice;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource(value = {"classpath:resource.properties"})
public class PictureServiceImpl implements PictureSerice {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(MultipartFile uploadFile){
        Map resultMap = new HashMap<>();
        try {
        String oldName = uploadFile.getOriginalFilename();
        String newName = IDUtils.genImageName();
        newName=newName+oldName.substring(oldName.lastIndexOf("."));
        String imagePath=new DateTime().toString("/yyyy/MM/dd");
        boolean result= FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_BASE_PATH,imagePath,newName,uploadFile.getInputStream());
        if (!result){
            resultMap.put("error",1);
            resultMap.put("message","文件上传失败");
            return resultMap;
        }
        resultMap.put("error",0);
        resultMap.put("url",IMAGE_BASE_URL+imagePath+"/"+newName);
        return resultMap;
        } catch (Exception e) {
            resultMap.put("error",1);
            resultMap.put("message","Upload Error");
            return resultMap;
        }
    }
}
