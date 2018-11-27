package com.taotao.manager.controller;

import com.taotao.manager.service.ContentCategoryService;
import com.taotao.common.utils.EUTreenode;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreenode> getContentList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        List<EUTreenode> list = contentCategoryService.getCategoryList(parentId);
        return list;

    }

    @ResponseBody
    @RequestMapping("/create")
    public TaotaoResult createContentCategory(long parentId, String name) {
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }
}
