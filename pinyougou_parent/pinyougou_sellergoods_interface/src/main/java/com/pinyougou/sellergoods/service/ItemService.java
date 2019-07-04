package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbItem;							  
import entity.PageResult;
import entity.Result;
/**
 * item服务层接口
 * @author jieweili
 *
 */
public interface ItemService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbItem> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param item
     * @return
     */
    public Result saveItem(TbItem item);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbItem getItemById(Long id);

    /**
     * 修改对象
     * @param item
     * @return
     */
    Result updateItem(TbItem item);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeItem(Long[] idList);

    /**
     * 根据条件分页查询
     * @param item
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listItemByExample(TbItem item, Integer pageNum, Integer pageSize);
 
}
