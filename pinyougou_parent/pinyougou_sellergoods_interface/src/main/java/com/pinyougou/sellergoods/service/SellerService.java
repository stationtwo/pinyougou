package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbSeller;							  
import entity.PageResult;
import entity.Result;
/**
 * seller服务层接口
 * @author jieweili
 *
 */
public interface SellerService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbSeller> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param seller
     * @return
     */
    public Result saveSeller(TbSeller seller);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbSeller getSellerById(String id);

    /**
     * 修改对象
     * @param seller
     * @return
     */
    Result updateSeller(TbSeller seller);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeSeller(String[] idList);

    /**
     * 根据条件分页查询
     * @param seller
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listSellerByExample(TbSeller seller, Integer pageNum, Integer pageSize);


    /**
     * 修改状态吗
     * @param id
     * @param status
     * @return
     */
    Result update(String id,String status);
}
