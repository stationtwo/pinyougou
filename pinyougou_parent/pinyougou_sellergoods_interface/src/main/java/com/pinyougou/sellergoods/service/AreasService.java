package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbAreas;							  
import entity.PageResult;
import entity.Result;
/**
 * areas服务层接口
 * @author jieweili
 *
 */
public interface AreasService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbAreas> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param areas
     * @return
     */
    public Result saveAreas(TbAreas areas);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbAreas getAreasById(Integer id);

    /**
     * 修改对象
     * @param areas
     * @return
     */
    Result updateAreas(TbAreas areas);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeAreas(Integer[] idList);

    /**
     * 根据条件分页查询
     * @param areas
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listAreasByExample(TbAreas areas, Integer pageNum, Integer pageSize);
 
}
