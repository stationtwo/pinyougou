package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import com.pinyougou.group.Specification;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;									  

import entity.PageResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 * @author jieweili
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
	@Transactional
    @Override
    public Result saveSpecification(Specification specification) {
        TbSpecification spec = specification.getTbSpecification();
        specificationMapper.insert(spec);

        int count = 0;
        List<TbSpecificationOption> optionList = specification.getOptionList();
        Long specId = spec.getId();
        if (ObjectUtils.notEmpty(optionList)) {
            for (TbSpecificationOption tbSpecificationOption : optionList) {
                tbSpecificationOption.setSpecId(specId);
                count += specificationOptionMapper.insertSelective(tbSpecificationOption);
            }
        }
        return ResultUtils.msg(optionList,count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public Specification getSpecificationById(Long id) {
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);

        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(tbSpecification.getId());
        List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
        return new Specification(tbSpecification, optionList);

    }

	/**
	 * 修改
	 */
    @Transactional
    @Override
    public Result updateSpecification(Specification specification) {
        TbSpecification spec = specification.getTbSpecification();
        specificationMapper.updateByPrimaryKeySelective(spec);
        Long specId = spec.getId();

        //删除原有的规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(specId);
        specificationOptionMapper.deleteByExample(example);
        int count = 0;

        //循环插入选项
        List<TbSpecificationOption> optionList = specification.getOptionList();
        if (ObjectUtils.notEmpty(optionList)) {
            for (TbSpecificationOption tbSpecificationOption : optionList) {
                tbSpecificationOption.setSpecId(specId);
                count += specificationOptionMapper.insertSelective(tbSpecificationOption);
            }
        }
        return ResultUtils.msg(optionList,count);
    }

	/**
	 * 批量删除
	 */
    @Transactional
    @Override
    public Result removeSpecification(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = specificationMapper.deleteByPrimaryKey(id);
                TbSpecificationOptionExample example = new TbSpecificationOptionExample();
                TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
                criteria.andSpecIdEqualTo(id);
                specificationOptionMapper.deleteByExample(example);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listSpecificationByExample(TbSpecification specification, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbSpecificationExample example = new TbSpecificationExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(specification)) {
            if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}

        }
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public List<Map> selectSpecList() {

        return specificationMapper.selectSpecList();
    }
}
