package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Result saveBrand(TbBrand brand) {
        int count = brandMapper.insertSelective(brand);
        return ResultUtils.msg(count);
    }

    @Override
    public TbBrand getBrandById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result updateBrand(TbBrand brand) {
        int i = brandMapper.updateByPrimaryKeySelective(brand);
        return ResultUtils.msg(i);
    }

    @Override
    public Result removeBrand(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = brandMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

    @Override
    public PageResult listBrandByExample(TbBrand brand, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(brand)) {
            if (ObjectUtils.notEmpty(brand.getName())) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if (ObjectUtils.notEmpty(brand.getFirstChar())) {
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
