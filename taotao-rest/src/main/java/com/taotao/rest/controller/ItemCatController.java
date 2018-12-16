package com.taotao.rest.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        String json = JsonUtils.objectToJson(catResult);
        String result = callback + "(" + json + ");";
        return result;
    }

//    @RequestMapping(value = "/itemcat/list1")
//    @ResponseBody
//    public Object getItemCatList1(String callback){
//        CatResult catResult=itemCatService.getItemCatList();
//        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
//        mappingJacksonValue.setJsonpFunction(callback);
//        return mappingJacksonValue;
//    }
}
