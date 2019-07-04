package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbGoodsDesc;							  
import entity.PageResult;
import entity.Result;
/**
 * goodsDesc服务层接口
 * @author jieweili
 *
 */
public interface GoodsDescService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbGoodsDesc> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param goodsDesc
     * @return
     */
    public Result saveGoodsDesc(TbGoodsDesc goodsDesc);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbGoodsDesc getGoodsDescById(Long id);

    /**
     * 修改对象
     * @param goodsDesc
     * @return
     */
    Result updateGoodsDesc(TbGoodsDesc goodsDesc);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeGoodsDesc(Long[] idList);

    /**
     * 根据条件分页查询
     * @param goodsDesc
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listGoodsDescByExample(TbGoodsDesc goodsDesc, Integer pageNum, Integer pageSize);
 
}
