package com.taotao.portal.service.impl;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.pojo.TbItemDesc;
import com.taotao.common.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@PropertySource(value = {"classpath:resource.properties"}, ignoreResourceNotFound = true)
public class ItemServiceImpl implements ItemService {
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Override
    public ItemInfo getItemById(long itemId) {
        try {
//            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            String json = HttpClientUtil.doGet("http://localhost:8081/rest/item/info/" + itemId);
            if (!StringUtils.isBlank(json)) {
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
                if (taotaoResult.getStatus() == 200) {
                    ItemInfo item = (ItemInfo) taotaoResult.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemDescById(long itemId) {
        try {
//            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            String json = HttpClientUtil.doGet("http://localhost:8081/rest/item/desc/" + itemId);
            if (!StringUtils.isBlank(json)) {
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
                if (taotaoResult.getStatus() == 200) {
                    TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
                    String result = itemDesc.getItemDesc();
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParam(long itemId) {
        try {
//            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            String json = HttpClientUtil.doGet("http://localhost:8081/rest/item/param/" + itemId);
            if (!StringUtils.isBlank(json)) {
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
                if (taotaoResult.getStatus() == 200) {
                    TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
                    String result = itemParamItem.getParamData();
                    List<Map> jsonList = JsonUtils.jsonToList(result, Map.class);
                    StringBuffer sb = new StringBuffer();
                    sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
                    sb.append("    <tbody>\n");
                    for (Map m1 : jsonList) {
                        sb.append("        <tr>\n");
                        sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
                        sb.append("        </tr>\n");
                        List<Map> list2 = (List<Map>) m1.get("params");
                        for (Map m2 : list2) {
                            sb.append("        <tr>\n");
                            sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
                            sb.append("            <td>" + m2.get("v") + "</td>\n");
                            sb.append("        </tr>\n");
                        }
                    }
                    sb.append("    </tbody>\n");
                    sb.append("</table>");
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
