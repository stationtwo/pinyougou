package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    /**
     * 更改用户状态
     * @param sellerId
     * @param status
     * @return
     */
    @RequestMapping("/update")
    public Result update(String sellerId,String status) {
        Result update = null;
        try {
            return update = sellerService.update(sellerId, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更改失败");
        }

    }

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param seller
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbSeller seller, Integer pageNum, Integer pageSize){
        return sellerService.listSellerByExample(seller,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeSeller")
    public Result removeSeller(String[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return sellerService.removeSeller(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param seller
     * @return
     */
    @RequestMapping("/updateSeller")
    public Result updateSeller(@RequestBody(required = false) TbSeller seller) {
        if (!ObjectUtils.notEmpty(seller)) {
            return new Result(false, "对象不存在");
        }
        try {
            return sellerService.updateSeller(seller);
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
    public TbSeller findOne(String id){
        return sellerService.getSellerById(id);
	}
	
    /**
     * 新增数据
     * @param seller
     * @return
     */
    @RequestMapping("/saveSeller")
    public Result saveSeller(@RequestBody(required = false) TbSeller seller){
        if (!ObjectUtils.notEmpty(seller)) {
            return new Result(false, "对象不存在");
        }
        try {
            return sellerService.saveSeller(seller);
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
    public List<TbSeller> findAll(){
        return sellerService.findAll();
    }
}
