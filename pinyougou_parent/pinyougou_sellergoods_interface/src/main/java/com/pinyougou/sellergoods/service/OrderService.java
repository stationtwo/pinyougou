package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbOrder;							  
import entity.PageResult;
import entity.Result;
/**
 * order服务层接口
 * @author jieweili
 *
 */
public interface OrderService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbOrder> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param order
     * @return
     */
    public Result saveOrder(TbOrder order);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbOrder getOrderById(Long id);

    /**
     * 修改对象
     * @param order
     * @return
     */
    Result updateOrder(TbOrder order);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeOrder(Long[] idList);

    /**
     * 根据条件分页查询
     * @param order
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listOrderByExample(TbOrder order, Integer pageNum, Integer pageSize);
 
}
