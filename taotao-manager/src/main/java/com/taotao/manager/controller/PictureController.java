package com.taotao.manager.controller;

import com.taotao.manager.service.PictureSerice;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictureController {
    @Autowired
    private PictureSerice pictureSerice;

    @ResponseBody
    @RequestMapping("/pic/upload")
    public String pictureUpload(MultipartFile uploadFile){
        Map result=pictureSerice.uploadPicture(uploadFile);
        String json= JsonUtils.objectToJson(result);
        return json;
    }
}
