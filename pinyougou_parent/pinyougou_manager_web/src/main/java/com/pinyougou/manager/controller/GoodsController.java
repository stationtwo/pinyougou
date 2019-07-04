package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.sellergoods.service.GoodsService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbGoods goods, Integer pageNum, Integer pageSize){
        return goodsService.listGoodsByExample(goods,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeGoods")
    public Result removeGoods(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return goodsService.removeGoods(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param goods
     * @return
     */
    @RequestMapping("/updateGoods")
    public Result updateGoods(@RequestBody(required = false) TbGoods goods) {
        if (!ObjectUtils.notEmpty(goods)) {
            return new Result(false, "对象不存在");
        }
        try {
            return goodsService.updateGoods(goods);
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
    public TbGoods findOne(Long id){
        return goodsService.getGoodsById(id);
	}
	
    /**
     * 新增数据
     * @param goods
     * @return
     */
    @RequestMapping("/saveGoods")
    public Result saveGoods(@RequestBody(required = false) TbGoods goods){
        if (!ObjectUtils.notEmpty(goods)) {
            return new Result(false, "对象不存在");
        }
        try {
            return goodsService.saveGoods(goods);
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
    public List<TbGoods> findAll(){
        return goodsService.findAll();
    }
}
