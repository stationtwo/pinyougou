package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbAreasMapper;
import com.pinyougou.pojo.TbAreas;
import com.pinyougou.pojo.TbAreasExample;
import com.pinyougou.pojo.TbAreasExample.Criteria;
import com.pinyougou.sellergoods.service.AreasService;
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
public class AreasServiceImpl implements AreasService {

    @Autowired
    private TbAreasMapper areasMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbAreas> findAll() {
        return areasMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbAreas> page = (Page<TbAreas>) areasMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveAreas(TbAreas areas) {
        int count = areasMapper.insertSelective(areas);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbAreas getAreasById(Integer id) {
        return areasMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateAreas(TbAreas areas) {
        int i = areasMapper.updateByPrimaryKeySelective(areas);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeAreas(Integer[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Integer id : idList) {
                int i = areasMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listAreasByExample(TbAreas areas, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbAreasExample example = new TbAreasExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(areas)) {
            			if(areas.getAreaid()!=null && areas.getAreaid().length()>0){
				criteria.andAreaidLike("%"+areas.getAreaid()+"%");
			}
			if(areas.getArea()!=null && areas.getArea().length()>0){
				criteria.andAreaLike("%"+areas.getArea()+"%");
			}
			if(areas.getCityid()!=null && areas.getCityid().length()>0){
				criteria.andCityidLike("%"+areas.getCityid()+"%");
			}

        }
        Page<TbAreas> page = (Page<TbAreas>) areasMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
