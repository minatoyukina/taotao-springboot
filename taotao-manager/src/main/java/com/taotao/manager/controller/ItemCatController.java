package com.taotao.manager.controller;

import com.taotao.manager.service.ItemCatService;
import com.taotao.common.utils.EUTreenode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("/list")
    public List<EUTreenode> getCatList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EUTreenode> list=itemCatService.getCatList(parentId);
        return list;
    }
}
