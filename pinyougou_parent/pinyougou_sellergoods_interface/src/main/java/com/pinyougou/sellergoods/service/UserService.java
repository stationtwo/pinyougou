package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbUser;							  
import entity.PageResult;
import entity.Result;
/**
 * user服务层接口
 * @author jieweili
 *
 */
public interface UserService {

    /**
     * 返回全部列表(已弃置)
     * @return
     */
    List<TbUser> findAll();
 
    /**
     * 返回分页对象(过时)
     * @param pageNum
     * @param pageSize
     * @return
     */
	 
    PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 添加数据
     * @param user
     * @return
     */
    public Result saveUser(TbUser user);

    /**
     * 根据id查询一个对象
     * @param id
     * @return
     */
    TbUser getUserById(Long id);

    /**
     * 修改对象
     * @param user
     * @return
     */
    Result updateUser(TbUser user);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result removeUser(Long[] idList);

    /**
     * 根据条件分页查询
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult listUserByExample(TbUser user, Integer pageNum, Integer pageSize);
 
}
