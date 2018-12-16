package com.taotao.rest.service;

import com.taotao.common.utils.TaotaoResult;

public interface ItemService {
    TaotaoResult getItemBaseInfo(long itemId);

    TaotaoResult getItemDesc(long itemId);

    TaotaoResult getItemParam(long itemId);
}
