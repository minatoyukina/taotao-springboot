package com.taotao.manager.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.pojo.TbItemParam;

public interface ItemParamService {
    TaotaoResult getItemParamByCid(long cid);
    TaotaoResult insertItemParam(TbItemParam itemParam);
}
