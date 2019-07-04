package com.pinyougou.manager.controller;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.group.Specification;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杰威力
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param specification
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbSpecification specification, Integer pageNum, Integer pageSize){
        return specificationService.listSpecificationByExample(specification,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeSpecification")
    public Result removeSpecification(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return specificationService.removeSpecification(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param specification
     * @return
     */
    @RequestMapping("/updateSpecification")
    public Result updateSpecification(@RequestBody(required = false) Specification specification) {
        if (!ObjectUtils.notEmpty(specification)) {
            return new Result(false, "对象不存在");
        }
        try {
            return specificationService.updateSpecification(specification);
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
    public Specification findOne(Long id){
        return specificationService.getSpecificationById(id);
	}
	
    /**
     * 新增数据
     * @param specification
     * @return
     */
    @RequestMapping("/saveSpecification")
    public Result saveSpecification(@RequestBody Specification specification){
        if (!ObjectUtils.notEmpty(specification)) {
            return new Result(false, "对象不存在");
    }
        try {
            return specificationService.saveSpecification(specification);
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
    public List<TbSpecification> findAll(){
        return specificationService.findAll();
    }

    /**
     * 查询所有规格列表封装map
     */
    @RequestMapping("/selectSpecList")
    public List<Map> selectSpecList(){
        return specificationService.selectSpecList();
    }
}
