package com.taotao.manager.controller;

import com.taotao.manager.service.ItemParamService;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/item/param")
@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable long itemCatId){
        TaotaoResult result=itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    @ResponseBody
    @RequestMapping("/save/{cid}")
    public TaotaoResult insertItemParam(@PathVariable long cid,String paramData){
        TbItemParam itemParam=new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        TaotaoResult result=itemParamService.insertItemParam(itemParam);
        return result;
    }
}
