package com.pinyougou.sellergoods.service;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;
import entity.Result;
/**
 * brand服务层接口
 * @author jieweili
 *
 */
public interface BrandService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbBrand> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param brand
     * @return
     */
    public Result saveBrand(TbBrand brand);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbBrand getBrandById(Long id);

    /**
     * 修改对象
     * @param brand
     * @return
     */
    Result updateBrand(TbBrand brand);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeBrand(Long[] idList);

    /**
     * 根据条件分页查询
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listBrandByExample(TbBrand brand, Integer pageNum, Integer pageSize);

    /**
     * 品牌下拉框列表
     * @return
     */
    List<Map> selectOptionList();
 
}
