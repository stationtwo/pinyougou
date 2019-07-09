package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.pojo.TbTypeTemplateExample.Criteria;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;
/**
 * 服务实现层
 * @author jieweili
 *
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper typeTemplateMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbTypeTemplate> findAll() {
        return typeTemplateMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveTypeTemplate(TbTypeTemplate typeTemplate) {
        int count = typeTemplateMapper.insertSelective(typeTemplate);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbTypeTemplate getTypeTemplateById(Long id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateTypeTemplate(TbTypeTemplate typeTemplate) {
        int i = typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeTypeTemplate(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = typeTemplateMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listTypeTemplateByExample(TbTypeTemplate typeTemplate, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(typeTemplate)) {
            			if(typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
				criteria.andNameLike("%"+typeTemplate.getName()+"%");
			}
			if(typeTemplate.getSpecIds()!=null && typeTemplate.getSpecIds().length()>0){
				criteria.andSpecIdsLike("%"+typeTemplate.getSpecIds()+"%");
			}
			if(typeTemplate.getBrandIds()!=null && typeTemplate.getBrandIds().length()>0){
				criteria.andBrandIdsLike("%"+typeTemplate.getBrandIds()+"%");
			}
			if(typeTemplate.getCustomAttributeItems()!=null && typeTemplate.getCustomAttributeItems().length()>0){
				criteria.andCustomAttributeItemsLike("%"+typeTemplate.getCustomAttributeItems()+"%");
			}

        }
        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public List<Map> listSpecification(Long id) {
        TbTypeTemplate tbTypeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
        List<Map> specList = JSON.parseArray(tbTypeTemplate.getSpecIds(), Map.class);
        for (Map map : specList) {
            //查询规格选项列表
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            example.createCriteria().andSpecIdEqualTo(new Long((Integer)map.get("id")));
            List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);

            map.put("optionList", optionList);

            // [{"id":27,"text":"网络","optionList":[{""optionName":移动3g"},{"optionName":"移动4g"}]},{"id":32,"text":"机身内存"},{"id":42,"text":"武琳洋"}]
        }
        return specList;
    }
}
