package com.taotao.manager.service;

import com.taotao.common.utils.EUTreenode;
import com.taotao.common.utils.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EUTreenode> getCategoryList(long parentId);
    TaotaoResult insertContentCategory(long parentId, String name);
}
