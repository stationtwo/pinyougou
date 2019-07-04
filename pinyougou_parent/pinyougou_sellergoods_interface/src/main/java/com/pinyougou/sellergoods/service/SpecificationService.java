package com.pinyougou.sellergoods.service;
import java.util.List;
import java.util.Map;

import com.pinyougou.group.Specification;
import com.pinyougou.pojo.TbSpecification;
import entity.PageResult;
import entity.Result;
/**
 * specification服务层接口
 * @author jieweili
 *
 */
public interface SpecificationService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbSpecification> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param specification
     * @return
     */
    public Result saveSpecification(Specification specification);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    Specification getSpecificationById(Long id);

    /**
     * 修改对象
     * @param specification
     * @return
     */
    Result updateSpecification(Specification specification);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeSpecification(Long[] idList);

    /**
     * 根据条件分页查询
     * @param specification
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listSpecificationByExample(TbSpecification specification, Integer pageNum, Integer pageSize);

    /**
     * 查询所有规格 封装map
     */
    List<Map> selectSpecList();

}
