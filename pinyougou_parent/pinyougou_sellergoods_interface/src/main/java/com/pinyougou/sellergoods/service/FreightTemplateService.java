package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbFreightTemplate;							  
import entity.PageResult;
import entity.Result;
/**
 * freightTemplate服务层接口
 * @author jieweili
 *
 */
public interface FreightTemplateService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbFreightTemplate> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param freightTemplate
     * @return
     */
    public Result saveFreightTemplate(TbFreightTemplate freightTemplate);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbFreightTemplate getFreightTemplateById(Long id);

    /**
     * 修改对象
     * @param freightTemplate
     * @return
     */
    Result updateFreightTemplate(TbFreightTemplate freightTemplate);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeFreightTemplate(Long[] idList);

    /**
     * 根据条件分页查询
     * @param freightTemplate
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listFreightTemplateByExample(TbFreightTemplate freightTemplate, Integer pageNum, Integer pageSize);
 
}
