package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.sellergoods.service.OrderService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param order
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbOrder order, Integer pageNum, Integer pageSize){
        return orderService.listOrderByExample(order,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeOrder")
    public Result removeOrder(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return orderService.removeOrder(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param order
     * @return
     */
    @RequestMapping("/updateOrder")
    public Result updateOrder(@RequestBody(required = false) TbOrder order) {
        if (!ObjectUtils.notEmpty(order)) {
            return new Result(false, "对象不存在");
        }
        try {
            return orderService.updateOrder(order);
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
    public TbOrder findOne(Long id){
        return orderService.getOrderById(id);
	}
	
    /**
     * 新增数据
     * @param order
     * @return
     */
    @RequestMapping("/saveOrder")
    public Result saveOrder(@RequestBody(required = false) TbOrder order){
        if (!ObjectUtils.notEmpty(order)) {
            return new Result(false, "对象不存在");
        }
        try {
            return orderService.saveOrder(order);
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
    public List<TbOrder> findAll(){
        return orderService.findAll();
    }
}
