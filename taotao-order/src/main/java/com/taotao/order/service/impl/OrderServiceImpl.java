package com.taotao.order.service.impl;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.mapper.TbOrderItemMapper;
import com.taotao.common.mapper.TbOrderMapper;
import com.taotao.common.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.common.pojo.TbOrder;
import com.taotao.common.pojo.TbOrderItem;
import com.taotao.common.pojo.TbOrderShipping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;

    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;

    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        String string = jedisClient.get(ORDER_GEN_KEY);
        if (StringUtils.isBlank(string)) {
            jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        long orderId = jedisClient.incr(ORDER_GEN_KEY);
        order.setOrderId(orderId + "");
        order.setStatus(1);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setBuyerRate(0);
        orderMapper.insert(order);
        for (TbOrderItem orderItem : itemList) {
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            orderItem.setId(orderDetailId + "");
            orderItem.setOrderId(orderId + "");
            orderItemMapper.insert(orderItem);
        }
        orderShipping.setOrderId(orderId + "");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);
        return TaotaoResult.ok(orderId);
    }
}
