package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbCities;							  
import entity.PageResult;
import entity.Result;
/**
 * cities服务层接口
 * @author jieweili
 *
 */
public interface CitiesService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbCities> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param cities
     * @return
     */
    public Result saveCities(TbCities cities);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbCities getCitiesById(Integer id);

    /**
     * 修改对象
     * @param cities
     * @return
     */
    Result updateCities(TbCities cities);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeCities(Integer[] idList);

    /**
     * 根据条件分页查询
     * @param cities
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listCitiesByExample(TbCities cities, Integer pageNum, Integer pageSize);
 
}
