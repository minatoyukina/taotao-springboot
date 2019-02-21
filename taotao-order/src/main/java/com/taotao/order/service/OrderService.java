package com.taotao.order.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.pojo.TbOrder;
import com.taotao.common.pojo.TbOrderItem;
import com.taotao.common.pojo.TbOrderShipping;

import java.util.List;

public interface OrderService {
    TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
