package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;
import entity.ResultInfo;

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
    public ResultInfo saveBrand(TbBrand brand);
}
