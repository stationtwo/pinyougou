package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbProvinces;							  
import entity.PageResult;
import entity.Result;
/**
 * provinces服务层接口
 * @author jieweili
 *
 */
public interface ProvincesService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbProvinces> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param provinces
     * @return
     */
    public Result saveProvinces(TbProvinces provinces);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbProvinces getProvincesById(Integer id);

    /**
     * 修改对象
     * @param provinces
     * @return
     */
    Result updateProvinces(TbProvinces provinces);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeProvinces(Integer[] idList);

    /**
     * 根据条件分页查询
     * @param provinces
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listProvincesByExample(TbProvinces provinces, Integer pageNum, Integer pageSize);
 
}
