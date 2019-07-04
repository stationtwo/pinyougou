package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbContentCategory;							  
import entity.PageResult;
import entity.Result;
/**
 * contentCategory服务层接口
 * @author jieweili
 *
 */
public interface ContentCategoryService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbContentCategory> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param contentCategory
     * @return
     */
    public Result saveContentCategory(TbContentCategory contentCategory);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbContentCategory getContentCategoryById(Long id);

    /**
     * 修改对象
     * @param contentCategory
     * @return
     */
    Result updateContentCategory(TbContentCategory contentCategory);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeContentCategory(Long[] idList);

    /**
     * 根据条件分页查询
     * @param contentCategory
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listContentCategoryByExample(TbContentCategory contentCategory, Integer pageNum, Integer pageSize);
 
}
