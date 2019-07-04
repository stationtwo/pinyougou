package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbOrderItem;							  
import entity.PageResult;
import entity.Result;
/**
 * orderItem服务层接口
 * @author jieweili
 *
 */
public interface OrderItemService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbOrderItem> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param orderItem
     * @return
     */
    public Result saveOrderItem(TbOrderItem orderItem);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbOrderItem getOrderItemById(Long id);

    /**
     * 修改对象
     * @param orderItem
     * @return
     */
    Result updateOrderItem(TbOrderItem orderItem);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeOrderItem(Long[] idList);

    /**
     * 根据条件分页查询
     * @param orderItem
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listOrderItemByExample(TbOrderItem orderItem, Integer pageNum, Integer pageSize);
 
}
