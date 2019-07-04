package com.pinyougou.sellergoods.service;

import java.util.List;

import com.pinyougou.pojo.TbItemCat;
import entity.PageResult;
import entity.Result;

/**
 * itemCat服务层接口
 *
 * @author jieweili
 */
public interface ItemCatService {

    /**
     * 返回全部列表(已弃置)
     *
     * @return
     */
    List<TbItemCat> findAll();

    /**
     * 返回分页对象(过时)
     *
     * @param pageNum
     * @param pageSize
     * @return
     */

    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     *
     * @param itemCat
     * @return
     */
    public Result saveItemCat(TbItemCat itemCat);

    /**
     * 根据id查询一个对象
     *
     * @param id
     * @return
     */
    TbItemCat getItemCatById(Long id);

    /**
     * 修改对象
     *
     * @param itemCat
     * @return
     */
    Result updateItemCat(TbItemCat itemCat);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    Result removeItemCat(Long[] idList);

    /**
     * 根据条件分页查询
     *
     * @param itemCat
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listItemCatByExample(TbItemCat itemCat, Integer pageNum, Integer pageSize);

    /**
     * 根据父id查询列表
     *
     * @param parentId
     * @return
     */
    List<TbItemCat> listItemCatByParentId(Long parentId);

}
