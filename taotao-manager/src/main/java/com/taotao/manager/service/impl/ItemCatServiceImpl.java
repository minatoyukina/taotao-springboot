package com.taotao.manager.service.impl;

import com.taotao.manager.service.ItemCatService;
import com.taotao.common.utils.EUTreenode;
import com.taotao.common.mapper.TbItemCatMapper;
import com.taotao.common.pojo.TbItemCat;
import com.taotao.common.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreenode> getCatList(long parentId) {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list=itemCatMapper.selectByExample(example);
        List<EUTreenode> resultList=new ArrayList<>();
        for (TbItemCat tbItemCat:list){
            EUTreenode node=new EUTreenode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }
}
