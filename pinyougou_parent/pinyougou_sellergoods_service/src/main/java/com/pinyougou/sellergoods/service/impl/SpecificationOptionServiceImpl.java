package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojo.TbSpecificationOptionExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationOptionService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;									  

import entity.PageResult;

/**
 * 服务实现层
 * @author jieweili
 *
 */
@Service
public class SpecificationOptionServiceImpl implements SpecificationOptionService {

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbSpecificationOption> findAll() {
        return specificationOptionMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecificationOption> page = (Page<TbSpecificationOption>) specificationOptionMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveSpecificationOption(TbSpecificationOption specificationOption) {
        int count = specificationOptionMapper.insertSelective(specificationOption);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbSpecificationOption getSpecificationOptionById(Long id) {
        return specificationOptionMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateSpecificationOption(TbSpecificationOption specificationOption) {
        int i = specificationOptionMapper.updateByPrimaryKeySelective(specificationOption);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeSpecificationOption(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = specificationOptionMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listSpecificationOptionByExample(TbSpecificationOption specificationOption, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(specificationOption)) {
            			if(specificationOption.getOptionName()!=null && specificationOption.getOptionName().length()>0){
				criteria.andOptionNameLike("%"+specificationOption.getOptionName()+"%");
			}

        }
        Page<TbSpecificationOption> page = (Page<TbSpecificationOption>) specificationOptionMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
