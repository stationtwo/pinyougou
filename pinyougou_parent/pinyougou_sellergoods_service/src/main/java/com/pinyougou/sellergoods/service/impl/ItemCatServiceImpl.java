package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.pojo.TbItemCatExample.Criteria;
import com.pinyougou.sellergoods.service.ItemCatService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;


/**
 * 服务实现层
 *
 * @author jieweili
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 查询全部(过时)
     */
    @Override
    public List<TbItemCat> findAll() {
        return itemCatMapper.selectByExample(null);
    }

    /**
     * 按分页查询(过时)
     */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public Result saveItemCat(TbItemCat itemCat) {
        int count = itemCatMapper.insertSelective(itemCat);
        return ResultUtils.msg(count);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbItemCat getItemCatById(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改
     */
    @Override
    public Result updateItemCat(TbItemCat itemCat) {
        int i = itemCatMapper.updateByPrimaryKeySelective(itemCat);
        return ResultUtils.msg(i);
    }

    /**
     * 批量删除
     */
    @Override
    public Result removeItemCat(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = itemCatMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

    /**
     * 条件查询 加载所有列表
     */
    @Override
    public PageResult listItemCatByExample(TbItemCat itemCat, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(itemCat)) {
            if (itemCat.getName() != null && itemCat.getName().length() > 0) {
                criteria.andNameLike("%" + itemCat.getName() + "%");
            }

        }
        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());

    }

    @Override
    public List<TbItemCat> listItemCatByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        return itemCatMapper.selectByExample(example);
    }
}
