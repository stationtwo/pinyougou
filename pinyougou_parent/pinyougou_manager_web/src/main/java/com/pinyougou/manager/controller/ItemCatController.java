package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杰威力
 * 2019/7/3 18:41
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    /**
     * 根据上级id查询所有
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/listItemCat")
    public List<TbItemCat> listItemCat(@RequestParam(defaultValue = "0",required = false) Long parentId) {
        return itemCatService.listItemCatByParentId(parentId);
    }
    /**
     * 查询所有 (包含模糊查询和分页)
     * @param itemCat
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbItemCat itemCat, Integer pageNum, Integer pageSize){
        return itemCatService.listItemCatByExample(itemCat,pageNum, pageSize);
    }

    /**
     * 删除方法
     *
     * @param idList
     * @return
     */
    @RequestMapping("/removeItemCat")
    public Result removeItemCat(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return itemCatService.removeItemCat(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     *
     * @param itemCat
     * @return
     */
    @RequestMapping("/updateItemCat")
    public Result updateItemCat(@RequestBody(required = false) TbItemCat itemCat) {
        if (!ObjectUtils.notEmpty(itemCat)) {
            return new Result(false, "对象不存在");
        }
        try {
            return itemCatService.updateItemCat(itemCat);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 根据id查询
     *
     * @return
     */
    @RequestMapping("/findOne")
    public TbItemCat findOne(Long id) {
        return itemCatService.getItemCatById(id);
    }

    /**
     * 新增数据
     *
     * @param itemCat
     * @return
     */
    @RequestMapping("/saveItemCat")
    public Result saveItemCat(@RequestBody(required = false) TbItemCat itemCat) {
        if (!ObjectUtils.notEmpty(itemCat)) {
            return new Result(false, "对象不存在");
        }
        try {
            return itemCatService.saveItemCat(itemCat);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 返回全部列表(已过时的列表查询方法)
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbItemCat> findAll() {
        return itemCatService.findAll();
    }

}
