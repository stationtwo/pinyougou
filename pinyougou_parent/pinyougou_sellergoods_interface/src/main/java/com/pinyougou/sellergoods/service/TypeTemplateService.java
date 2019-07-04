package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbTypeTemplate;							  
import entity.PageResult;
import entity.Result;
/**
 * typeTemplate服务层接口
 * @author jieweili
 *
 */
public interface TypeTemplateService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbTypeTemplate> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param typeTemplate
     * @return
     */
    public Result saveTypeTemplate(TbTypeTemplate typeTemplate);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbTypeTemplate getTypeTemplateById(Long id);

    /**
     * 修改对象
     * @param typeTemplate
     * @return
     */
    Result updateTypeTemplate(TbTypeTemplate typeTemplate);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeTypeTemplate(Long[] idList);

    /**
     * 根据条件分页查询
     * @param typeTemplate
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listTypeTemplateByExample(TbTypeTemplate typeTemplate, Integer pageNum, Integer pageSize);
 
}
