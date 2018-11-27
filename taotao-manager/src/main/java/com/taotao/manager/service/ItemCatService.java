package com.taotao.manager.service;

import com.taotao.common.utils.EUTreenode;

import java.util.List;

public interface ItemCatService {
    List<EUTreenode> getCatList(long parentId);
}
