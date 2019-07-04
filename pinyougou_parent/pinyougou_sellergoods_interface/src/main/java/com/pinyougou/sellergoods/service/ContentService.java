package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbContent;							  
import entity.PageResult;
import entity.Result;
/**
 * content服务层接口
 * @author jieweili
 *
 */
public interface ContentService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbContent> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param content
     * @return
     */
    public Result saveContent(TbContent content);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbContent getContentById(Long id);

    /**
     * 修改对象
     * @param content
     * @return
     */
    Result updateContent(TbContent content);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeContent(Long[] idList);

    /**
     * 根据条件分页查询
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listContentByExample(TbContent content, Integer pageNum, Integer pageSize);
 
}
