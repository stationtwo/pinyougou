package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

public interface BrandService {

    /**
     * 返回全部列表
     * @return
     */
    List<TbBrand> findAll();

    /**
     * 返回分页数据
     * @return
     */
    PageResult findPage(Integer pageNum, Integer pageSize);
}
