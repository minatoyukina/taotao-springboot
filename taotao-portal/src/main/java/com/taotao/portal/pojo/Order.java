package com.taotao.portal.pojo;

import com.taotao.common.pojo.TbOrder;
import com.taotao.common.pojo.TbOrderItem;
import com.taotao.common.pojo.TbOrderShipping;

import java.util.List;

public class Order extends TbOrder {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
