package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.ResultInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 新增品牌数据
     * @param brand
     * @return
     */
    @RequestMapping("/saveBrand")
    public ResultInfo saveBrand(@RequestBody TbBrand brand){
        if (!ObjectUtils.notEmpty(brand)) {
            return new ResultInfo(false, "对象不存在");
        }
        if (ObjectUtils.isEmpty(brand.getName())) {
            return new ResultInfo(false, "品牌名不能为空");
        }
        if (ObjectUtils.isEmpty(brand.getFirstChar())) {
            return new ResultInfo(false, "首字母填写不正确,应为一个字母");
        }
        try {
            return brandService.saveBrand(brand);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultInfo(false, "未知的错误信息,请重试");
        }
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNum,Integer pageSize){
        return brandService.findPage(pageNum, pageSize);
    }

    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }
}
