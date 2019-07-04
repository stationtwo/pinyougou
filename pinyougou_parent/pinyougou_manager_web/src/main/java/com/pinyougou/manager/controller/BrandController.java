package com.pinyougou.manager.controller;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbBrand brand, Integer pageNum, Integer pageSize){
        return brandService.listBrandByExample(brand,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeBrand")
    public Result removeBrand(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return brandService.removeBrand(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param brand
     * @return
     */
    @RequestMapping("/updateBrand")
    public Result updateBrand(@RequestBody(required = false) TbBrand brand) {
        if (!ObjectUtils.notEmpty(brand)) {
            return new Result(false, "对象不存在");
        }
        try {
            return brandService.updateBrand(brand);
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
    public TbBrand findOne(Long id){
        return brandService.getBrandById(id);
	}
	
    /**
     * 新增数据
     * @param brand
     * @return
     */
    @RequestMapping("/saveBrand")
    public Result saveBrand(@RequestBody(required = false) TbBrand brand){
        if (!ObjectUtils.notEmpty(brand)) {
            return new Result(false, "对象不存在");
        }
        try {
            return brandService.saveBrand(brand);
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
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }


}

