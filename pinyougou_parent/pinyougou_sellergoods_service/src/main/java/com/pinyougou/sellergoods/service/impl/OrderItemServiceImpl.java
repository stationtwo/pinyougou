package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbOrderItemMapper;
import com.pinyougou.pojo.TbOrderItem;
import com.pinyougou.pojo.TbOrderItemExample;
import com.pinyougou.pojo.TbOrderItemExample.Criteria;
import com.pinyougou.sellergoods.service.OrderItemService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;									  

import entity.PageResult;

/**
 * 服务实现层
 * @author jieweili
 *
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private TbOrderItemMapper orderItemMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbOrderItem> findAll() {
        return orderItemMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbOrderItem> page = (Page<TbOrderItem>) orderItemMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveOrderItem(TbOrderItem orderItem) {
        int count = orderItemMapper.insertSelective(orderItem);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbOrderItem getOrderItemById(Long id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateOrderItem(TbOrderItem orderItem) {
        int i = orderItemMapper.updateByPrimaryKeySelective(orderItem);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeOrderItem(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = orderItemMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listOrderItemByExample(TbOrderItem orderItem, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbOrderItemExample example = new TbOrderItemExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(orderItem)) {
            			if(orderItem.getTitle()!=null && orderItem.getTitle().length()>0){
				criteria.andTitleLike("%"+orderItem.getTitle()+"%");
			}
			if(orderItem.getPicPath()!=null && orderItem.getPicPath().length()>0){
				criteria.andPicPathLike("%"+orderItem.getPicPath()+"%");
			}
			if(orderItem.getSellerId()!=null && orderItem.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+orderItem.getSellerId()+"%");
			}

        }
        Page<TbOrderItem> page = (Page<TbOrderItem>) orderItemMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
