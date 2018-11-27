package com.taotao.manager.controller;

import com.taotao.manager.service.ContentService;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content){
        TaotaoResult result=contentService.insertContent(content);
        return result;
    }
}
