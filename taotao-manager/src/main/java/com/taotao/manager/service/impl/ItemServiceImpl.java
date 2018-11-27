package com.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.service.ItemService;
import com.taotao.common.utils.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.mapper.TbItemDescMapper;
import com.taotao.common.mapper.TbItemMapper;
import com.taotao.common.mapper.TbItemParamItemMapper;
import com.taotao.common.pojo.TbItem;
import com.taotao.common.pojo.TbItemDesc;
import com.taotao.common.pojo.TbItemExample;
import com.taotao.common.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
//        TbItem item=itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list!=null&&list.size()>0){
            TbItem item =list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example=new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        EUDataGridResult result=new EUDataGridResult();
        result.setRows(list);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }



    @Override
    public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        TaotaoResult result = insertItemDesc(itemId, desc);
        if (result.getStatus()!=200){
            throw new Exception();
        }

        result=insertItemParamItem(itemId,itemParams);
        if (result.getStatus()!=200){
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemDesc(long itemId,String desc){
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParamItem(long itemId,String itemParams){
        TbItemParamItem itemParamItem=new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();
    }
}
