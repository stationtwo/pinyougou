package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbPayLog;							  
import entity.PageResult;
import entity.Result;
/**
 * payLog服务层接口
 * @author jieweili
 *
 */
public interface PayLogService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbPayLog> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param payLog
     * @return
     */
    public Result savePayLog(TbPayLog payLog);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbPayLog getPayLogById(String id);

    /**
     * 修改对象
     * @param payLog
     * @return
     */
    Result updatePayLog(TbPayLog payLog);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removePayLog(String[] idList);

    /**
     * 根据条件分页查询
     * @param payLog
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listPayLogByExample(TbPayLog payLog, Integer pageNum, Integer pageSize);
 
}
