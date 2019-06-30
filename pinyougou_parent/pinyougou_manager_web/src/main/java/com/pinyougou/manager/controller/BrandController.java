package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbBrand brand, Integer pageNum, Integer pageSize){
        return brandService.listBrandByExample(brand,pageNum, pageSize);
    }


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
     * 修改品牌对象
     * @param brand
     * @return
     */
    @RequestMapping("/updateBrand")
    public Result updateBrand(@RequestBody(required = false) TbBrand brand) {
        if (!ObjectUtils.notEmpty(brand)) {
            return new Result(false, "对象不存在");
        }
        if (ObjectUtils.isEmpty(brand.getName())) {
            return new Result(false, "品牌名不能为空");
        }
        if (ObjectUtils.isEmpty(brand.getFirstChar())) {
            return new Result(false, "首字母填写不正确,应为一个字母");
        }
        try {
            return brandService.updateBrand(brand);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 根据id查询品牌
     * @return
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.getBrandById(id);
    }

    /**
     * 新增品牌数据
     * @param brand
     * @return
     */
    @RequestMapping("/saveBrand")
    public Result saveBrand(@RequestBody(required = false) TbBrand brand){
        if (!ObjectUtils.notEmpty(brand)) {
            return new Result(false, "对象不存在");
        }
        if (ObjectUtils.isEmpty(brand.getName())) {
            return new Result(false, "品牌名不能为空");
        }
        if (ObjectUtils.isEmpty(brand.getFirstChar())) {
            return new Result(false, "首字母填写不正确,应为一个字母");
        }
        try {
            return brandService.saveBrand(brand);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNum,Integer pageSize){
        return brandService.findPage(pageNum, pageSize);
    }*/

    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }
}
