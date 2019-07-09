package com.pinyougou.sellergoods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinyougou.group.GoodsGroup;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.pojo.TbGoodsExample.Criteria;
import com.pinyougou.sellergoods.service.GoodsService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;

import entity.PageResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 *
 * @author jieweili
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper goodsMapper;
    @Autowired
    private TbGoodsDescMapper goodsDescMapper;
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Autowired
    private TbBrandMapper brandMapper;
    @Autowired
    private TbSellerMapper sellerMapper;

    /**
     * 查询全部(过时)
     */
    @Override
    public List<TbGoods> findAll() {
        return goodsMapper.selectByExample(null);
    }

    /**
     * 按分页查询(过时)
     */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    @Transactional
    public Result saveGoods(GoodsGroup goodsGroup) {
        goodsGroup.getGoods().setAuditStatus("0");
        int j = goodsMapper.insertSelective(goodsGroup.getGoods());//插入商品表
        goodsGroup.getGoodsDesc().setGoodsId(goodsGroup.getGoods().getId());
        int i = goodsDescMapper.insertSelective(goodsGroup.getGoodsDesc());//插入商品扩展数据

        Integer insert = null;
        if ("1".equals(goodsGroup.getGoods().getIsEnableSpec())) {
            //插入 sku
            for (TbItem tbItem : goodsGroup.getItemList()) {
                //查询标题title
                String title = goodsGroup.getGoods().getGoodsName();
                Map<String, Object> map = JSON.parseObject(tbItem.getSpec());
                for (String key : map.keySet()) {
                    title += " " + map.get(key);
                }
                tbItem.setTitle(title);
                setItemValues(goodsGroup, tbItem);
                insert = itemMapper.insert(tbItem);
            }
        } else {
            TbItem item = new TbItem();
            item.setTitle(goodsGroup.getGoods().getGoodsName());//商品 SPU+规格描述串作为SKU 名称
            item.setPrice(goodsGroup.getGoods().getPrice());//价格
            item.setStatus("1");//状态
            item.setIsDefault("1");//是否默认
            item.setNum(99999);//库存数量
            item.setSpec("{}");
            setItemValues(goodsGroup, item);
            insert = itemMapper.insert(item);

        }

        return ResultUtils.msg(insert);

    }

    private void setItemValues(GoodsGroup goodsGroup, TbItem tbItem) {
        tbItem.setGoodsId(goodsGroup.getGoods().getId());//商品 SPU 编号
        tbItem.setSellerId(goodsGroup.getGoods().getSellerId());//商家编号
        tbItem.setCategoryid(goodsGroup.getGoods().getCategory3Id());//商品分类编号（3 级）
        tbItem.setCreateTime(new Date());//创建日期
        tbItem.setUpdateTime(new Date());//修改日期

        //品牌名称
        TbBrand brand =
                brandMapper.selectByPrimaryKey(goodsGroup.getGoods().getBrandId());
        tbItem.setBrand(brand.getName());
        //分类名称
        TbItemCat itemCat =
                itemCatMapper.selectByPrimaryKey(goodsGroup.getGoods().getCategory3Id());
        tbItem.setCategory(itemCat.getName());
        //商家名称
        //TbSeller seller = sellerMapper.selectByPrimaryKey(goodsGroup.getGoods().getSellerId());
       // tbItem.setSeller(seller.getNickName());
        //图片地址（取 spu 的第一个图片）
        List<Map> imageList = JSON.parseArray(goodsGroup.getGoodsDesc().getItemImages(),
                Map.class);
        if (imageList.size() > 0) {
            tbItem.setImage((String) imageList.get(0).get("url"));
        }
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbGoods getGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改
     */
    @Override
    public Result updateGoods(TbGoods goods) {
        int i = goodsMapper.updateByPrimaryKeySelective(goods);
        return ResultUtils.msg(i);
    }

    /**
     * 批量删除
     */
    @Override
    public Result removeGoods(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = goodsMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

    /**
     * 条件查询 加载所有列表
     */
    @Override
    public PageResult listGoodsByExample(TbGoods goods, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbGoodsExample example = new TbGoodsExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(goods)) {
            if (goods.getSellerId() != null && goods.getSellerId().length() > 0) {
//				criteria.andSellerIdLike("%"+goods.getSellerId()+"%");
                criteria.andSellerIdEqualTo(goods.getSellerId());
            }
            if (goods.getGoodsName() != null && goods.getGoodsName().length() > 0) {
                criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
            }
            if (goods.getAuditStatus() != null && goods.getAuditStatus().length() > 0) {
                criteria.andAuditStatusLike("%" + goods.getAuditStatus() + "%");
            }
            if (goods.getIsMarketable() != null && goods.getIsMarketable().length() > 0) {
                criteria.andIsMarketableLike("%" + goods.getIsMarketable() + "%");
            }
            if (goods.getCaption() != null && goods.getCaption().length() > 0) {
                criteria.andCaptionLike("%" + goods.getCaption() + "%");
            }
            if (goods.getSmallPic() != null && goods.getSmallPic().length() > 0) {
                criteria.andSmallPicLike("%" + goods.getSmallPic() + "%");
            }
            if (goods.getIsEnableSpec() != null && goods.getIsEnableSpec().length() > 0) {
                criteria.andIsEnableSpecLike("%" + goods.getIsEnableSpec() + "%");
            }
            if (goods.getIsDelete() != null && goods.getIsDelete().length() > 0) {
                criteria.andIsDeleteLike("%" + goods.getIsDelete() + "%");
            }

        }
        Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());

    }
}
