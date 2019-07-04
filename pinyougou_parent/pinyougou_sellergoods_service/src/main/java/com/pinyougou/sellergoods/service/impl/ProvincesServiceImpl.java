package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbProvincesMapper;
import com.pinyougou.pojo.TbProvinces;
import com.pinyougou.pojo.TbProvincesExample;
import com.pinyougou.pojo.TbProvincesExample.Criteria;
import com.pinyougou.sellergoods.service.ProvincesService;
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
public class ProvincesServiceImpl implements ProvincesService {

    @Autowired
    private TbProvincesMapper provincesMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbProvinces> findAll() {
        return provincesMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbProvinces> page = (Page<TbProvinces>) provincesMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveProvinces(TbProvinces provinces) {
        int count = provincesMapper.insertSelective(provinces);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbProvinces getProvincesById(Integer id) {
        return provincesMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateProvinces(TbProvinces provinces) {
        int i = provincesMapper.updateByPrimaryKeySelective(provinces);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeProvinces(Integer[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Integer id : idList) {
                int i = provincesMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listProvincesByExample(TbProvinces provinces, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbProvincesExample example = new TbProvincesExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(provinces)) {
            			if(provinces.getProvinceid()!=null && provinces.getProvinceid().length()>0){
				criteria.andProvinceidLike("%"+provinces.getProvinceid()+"%");
			}
			if(provinces.getProvince()!=null && provinces.getProvince().length()>0){
				criteria.andProvinceLike("%"+provinces.getProvince()+"%");
			}

        }
        Page<TbProvinces> page = (Page<TbProvinces>) provincesMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
