package com.pinyougou.sellergoods.service;
import java.util.List;

import com.pinyougou.group.GoodsGroup;
import com.pinyougou.pojo.TbGoods;
import entity.PageResult;
import entity.Result;
/**
 * goods服务层接口
 * @author jieweili
 *
 */
public interface GoodsService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbGoods> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据 组合类
     * @param goodsGroup
     * @return
     */
    public Result saveGoods(GoodsGroup goodsGroup);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbGoods getGoodsById(Long id);

    /**
     * 修改对象
     * @param goods
     * @return
     */
    Result updateGoods(TbGoods goods);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeGoods(Long[] idList);

    /**
     * 根据条件分页查询
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listGoodsByExample(TbGoods goods, Integer pageNum, Integer pageSize);


 
}
