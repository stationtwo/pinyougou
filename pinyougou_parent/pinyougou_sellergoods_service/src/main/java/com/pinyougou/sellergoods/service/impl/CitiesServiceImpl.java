package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbCitiesMapper;
import com.pinyougou.pojo.TbCities;
import com.pinyougou.pojo.TbCitiesExample;
import com.pinyougou.pojo.TbCitiesExample.Criteria;
import com.pinyougou.sellergoods.service.CitiesService;
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
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private TbCitiesMapper citiesMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbCities> findAll() {
        return citiesMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbCities> page = (Page<TbCities>) citiesMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveCities(TbCities cities) {
        int count = citiesMapper.insertSelective(cities);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbCities getCitiesById(Integer id) {
        return citiesMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateCities(TbCities cities) {
        int i = citiesMapper.updateByPrimaryKeySelective(cities);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeCities(Integer[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Integer id : idList) {
                int i = citiesMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listCitiesByExample(TbCities cities, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbCitiesExample example = new TbCitiesExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(cities)) {
            			if(cities.getCityid()!=null && cities.getCityid().length()>0){
				criteria.andCityidLike("%"+cities.getCityid()+"%");
			}
			if(cities.getCity()!=null && cities.getCity().length()>0){
				criteria.andCityLike("%"+cities.getCity()+"%");
			}
			if(cities.getProvinceid()!=null && cities.getProvinceid().length()>0){
				criteria.andProvinceidLike("%"+cities.getProvinceid()+"%");
			}

        }
        Page<TbCities> page = (Page<TbCities>) citiesMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
