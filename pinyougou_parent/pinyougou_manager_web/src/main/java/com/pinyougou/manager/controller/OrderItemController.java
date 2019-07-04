package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbOrderItem;
import com.pinyougou.sellergoods.service.OrderItemService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Reference
    private OrderItemService orderItemService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param orderItem
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbOrderItem orderItem, Integer pageNum, Integer pageSize){
        return orderItemService.listOrderItemByExample(orderItem,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeOrderItem")
    public Result removeOrderItem(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return orderItemService.removeOrderItem(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param orderItem
     * @return
     */
    @RequestMapping("/updateOrderItem")
    public Result updateOrderItem(@RequestBody(required = false) TbOrderItem orderItem) {
        if (!ObjectUtils.notEmpty(orderItem)) {
            return new Result(false, "对象不存在");
        }
        try {
            return orderItemService.updateOrderItem(orderItem);
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
    public TbOrderItem findOne(Long id){
        return orderItemService.getOrderItemById(id);
	}
	
    /**
     * 新增数据
     * @param orderItem
     * @return
     */
    @RequestMapping("/saveOrderItem")
    public Result saveOrderItem(@RequestBody(required = false) TbOrderItem orderItem){
        if (!ObjectUtils.notEmpty(orderItem)) {
            return new Result(false, "对象不存在");
        }
        try {
            return orderItemService.saveOrderItem(orderItem);
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
    public List<TbOrderItem> findAll(){
        return orderItemService.findAll();
    }
}
