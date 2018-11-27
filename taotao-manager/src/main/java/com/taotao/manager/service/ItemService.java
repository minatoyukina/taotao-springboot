package com.taotao.manager.service;

import com.taotao.common.utils.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(long itemId);
    EUDataGridResult getItemList(int page, int rows);
    TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception;
}
