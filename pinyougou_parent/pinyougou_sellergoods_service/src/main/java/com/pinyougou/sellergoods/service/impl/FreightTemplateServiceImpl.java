package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbFreightTemplateMapper;
import com.pinyougou.pojo.TbFreightTemplate;
import com.pinyougou.pojo.TbFreightTemplateExample;
import com.pinyougou.pojo.TbFreightTemplateExample.Criteria;
import com.pinyougou.sellergoods.service.FreightTemplateService;
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
public class FreightTemplateServiceImpl implements FreightTemplateService {

    @Autowired
    private TbFreightTemplateMapper freightTemplateMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbFreightTemplate> findAll() {
        return freightTemplateMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbFreightTemplate> page = (Page<TbFreightTemplate>) freightTemplateMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveFreightTemplate(TbFreightTemplate freightTemplate) {
        int count = freightTemplateMapper.insertSelective(freightTemplate);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbFreightTemplate getFreightTemplateById(Long id) {
        return freightTemplateMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateFreightTemplate(TbFreightTemplate freightTemplate) {
        int i = freightTemplateMapper.updateByPrimaryKeySelective(freightTemplate);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeFreightTemplate(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = freightTemplateMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listFreightTemplateByExample(TbFreightTemplate freightTemplate, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbFreightTemplateExample example = new TbFreightTemplateExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(freightTemplate)) {
            			if(freightTemplate.getSellerId()!=null && freightTemplate.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+freightTemplate.getSellerId()+"%");
			}
			if(freightTemplate.getIsDefault()!=null && freightTemplate.getIsDefault().length()>0){
				criteria.andIsDefaultLike("%"+freightTemplate.getIsDefault()+"%");
			}
			if(freightTemplate.getName()!=null && freightTemplate.getName().length()>0){
				criteria.andNameLike("%"+freightTemplate.getName()+"%");
			}
			if(freightTemplate.getSendTimeType()!=null && freightTemplate.getSendTimeType().length()>0){
				criteria.andSendTimeTypeLike("%"+freightTemplate.getSendTimeType()+"%");
			}

        }
        Page<TbFreightTemplate> page = (Page<TbFreightTemplate>) freightTemplateMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
