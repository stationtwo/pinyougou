package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbSpecificationOption;							  
import entity.PageResult;
import entity.Result;
/**
 * specificationOption服务层接口
 * @author jieweili
 *
 */
public interface SpecificationOptionService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbSpecificationOption> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param specificationOption
     * @return
     */
    public Result saveSpecificationOption(TbSpecificationOption specificationOption);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbSpecificationOption getSpecificationOptionById(Long id);

    /**
     * 修改对象
     * @param specificationOption
     * @return
     */
    Result updateSpecificationOption(TbSpecificationOption specificationOption);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeSpecificationOption(Long[] idList);

    /**
     * 根据条件分页查询
     * @param specificationOption
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listSpecificationOptionByExample(TbSpecificationOption specificationOption, Integer pageNum, Integer pageSize);
 
}
