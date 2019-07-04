package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbAddress;							  
import entity.PageResult;
import entity.Result;
/**
 * address服务层接口
 * @author jieweili
 *
 */
public interface AddressService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbAddress> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param address
     * @return
     */
    public Result saveAddress(TbAddress address);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbAddress getAddressById(Long id);

    /**
     * 修改对象
     * @param address
     * @return
     */
    Result updateAddress(TbAddress address);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeAddress(Long[] idList);

    /**
     * 根据条件分页查询
     * @param address
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listAddressByExample(TbAddress address, Integer pageNum, Integer pageSize);
 
}
