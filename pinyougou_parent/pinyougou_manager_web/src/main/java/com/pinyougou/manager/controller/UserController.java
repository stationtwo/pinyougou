package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.sellergoods.service.UserService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbUser user, Integer pageNum, Integer pageSize){
        return userService.listUserByExample(user,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeUser")
    public Result removeUser(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return userService.removeUser(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody(required = false) TbUser user) {
        if (!ObjectUtils.notEmpty(user)) {
            return new Result(false, "对象不存在");
        }
        try {
            return userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 根据id查询
     * @return
     */
    @RequestMapping("/findOne")
    public TbUser findOne(Long id){
        return userService.getUserById(id);
	}
	
    /**
     * 新增数据
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public Result saveUser(@RequestBody(required = false) TbUser user){
        if (!ObjectUtils.notEmpty(user)) {
            return new Result(false, "对象不存在");
        }
        try {
            return userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }
    /**
     * 返回全部列表(已过时的列表查询方法)
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbUser> findAll(){
        return userService.findAll();
    }
}
