package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbGoodsDescExample;
import com.pinyougou.pojo.TbGoodsDescExample.Criteria;
import com.pinyougou.sellergoods.service.GoodsDescService;
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
public class GoodsDescServiceImpl implements GoodsDescService {

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbGoodsDesc> findAll() {
        return goodsDescMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbGoodsDesc> page = (Page<TbGoodsDesc>) goodsDescMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveGoodsDesc(TbGoodsDesc goodsDesc) {
        int count = goodsDescMapper.insertSelective(goodsDesc);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbGoodsDesc getGoodsDescById(Long id) {
        return goodsDescMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateGoodsDesc(TbGoodsDesc goodsDesc) {
        int i = goodsDescMapper.updateByPrimaryKeySelective(goodsDesc);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeGoodsDesc(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = goodsDescMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listGoodsDescByExample(TbGoodsDesc goodsDesc, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbGoodsDescExample example = new TbGoodsDescExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(goodsDesc)) {
            			if(goodsDesc.getIntroduction()!=null && goodsDesc.getIntroduction().length()>0){
				criteria.andIntroductionLike("%"+goodsDesc.getIntroduction()+"%");
			}
			if(goodsDesc.getSpecificationItems()!=null && goodsDesc.getSpecificationItems().length()>0){
				criteria.andSpecificationItemsLike("%"+goodsDesc.getSpecificationItems()+"%");
			}
			if(goodsDesc.getCustomAttributeItems()!=null && goodsDesc.getCustomAttributeItems().length()>0){
				criteria.andCustomAttributeItemsLike("%"+goodsDesc.getCustomAttributeItems()+"%");
			}
			if(goodsDesc.getItemImages()!=null && goodsDesc.getItemImages().length()>0){
				criteria.andItemImagesLike("%"+goodsDesc.getItemImages()+"%");
			}
			if(goodsDesc.getPackageList()!=null && goodsDesc.getPackageList().length()>0){
				criteria.andPackageListLike("%"+goodsDesc.getPackageList()+"%");
			}
			if(goodsDesc.getSaleService()!=null && goodsDesc.getSaleService().length()>0){
				criteria.andSaleServiceLike("%"+goodsDesc.getSaleService()+"%");
			}

        }
        Page<TbGoodsDesc> page = (Page<TbGoodsDesc>) goodsDescMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
