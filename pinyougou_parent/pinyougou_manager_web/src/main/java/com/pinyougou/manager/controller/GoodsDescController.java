package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.sellergoods.service.GoodsDescService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodsDesc")
public class GoodsDescController {

    @Reference
    private GoodsDescService goodsDescService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param goodsDesc
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbGoodsDesc goodsDesc, Integer pageNum, Integer pageSize){
        return goodsDescService.listGoodsDescByExample(goodsDesc,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeGoodsDesc")
    public Result removeGoodsDesc(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return goodsDescService.removeGoodsDesc(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param goodsDesc
     * @return
     */
    @RequestMapping("/updateGoodsDesc")
    public Result updateGoodsDesc(@RequestBody(required = false) TbGoodsDesc goodsDesc) {
        if (!ObjectUtils.notEmpty(goodsDesc)) {
            return new Result(false, "对象不存在");
        }
        try {
            return goodsDescService.updateGoodsDesc(goodsDesc);
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
    public TbGoodsDesc findOne(Long id){
        return goodsDescService.getGoodsDescById(id);
	}
	
    /**
     * 新增数据
     * @param goodsDesc
     * @return
     */
    @RequestMapping("/saveGoodsDesc")
    public Result saveGoodsDesc(@RequestBody(required = false) TbGoodsDesc goodsDesc){
        if (!ObjectUtils.notEmpty(goodsDesc)) {
            return new Result(false, "对象不存在");
        }
        try {
            return goodsDescService.saveGoodsDesc(goodsDesc);
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
    public List<TbGoodsDesc> findAll(){
        return goodsDescService.findAll();
    }
}
