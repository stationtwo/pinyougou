package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;
import entity.Result;

import java.util.List;

public interface BrandService {

    /**
     * 返回全部列表
     * @return
     */
    List<TbBrand> findAll();

    /**
     * 返回分页对象 PageResult
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加品牌数据
     * @param brand
     * @return
     */
    public Result saveBrand(TbBrand brand);

    /**
     * 根据id查询一个品牌对象
     * @param idList
     * @return
     */
    TbBrand getBrandById(Long idList);

    /**
     * 修改品牌对象
     * @param brand
     * @return
     */
    Result updateBrand(TbBrand brand);

    /**
     * 批量删除
     * @param id
     * @return
     */
    Result removeBrand(Long[] id);

    /**
     * 根据条件分页查询
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listBrandByExample(TbBrand brand,Integer pageNum, Integer pageSize);
}
