package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.LongToDoubleFunction;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param typeTemplate
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbTypeTemplate typeTemplate, Integer pageNum, Integer pageSize){
        return typeTemplateService.listTypeTemplateByExample(typeTemplate,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeTypeTemplate")
    public Result removeTypeTemplate(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return typeTemplateService.removeTypeTemplate(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/updateTypeTemplate")
    public Result updateTypeTemplate(@RequestBody(required = false) TbTypeTemplate typeTemplate) {
        if (!ObjectUtils.notEmpty(typeTemplate)) {
            return new Result(false, "对象不存在");
        }
        try {
            return typeTemplateService.updateTypeTemplate(typeTemplate);
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
    public TbTypeTemplate findOne(Long id){
        return typeTemplateService.getTypeTemplateById(id);
	}
	
    /**
     * 新增数据
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/saveTypeTemplate")
    public Result saveTypeTemplate(@RequestBody(required = false) TbTypeTemplate typeTemplate){
        if (!ObjectUtils.notEmpty(typeTemplate)) {
            return new Result(false, "对象不存在");
        }
        if (ObjectUtils.isEmpty(typeTemplate.getName())) {
            return new Result(false, "商品名称不能为空");
        }
        try {
            return typeTemplateService.saveTypeTemplate(typeTemplate);
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
    public List<TbTypeTemplate> findAll(){
        return typeTemplateService.findAll();
    }

    /**
     * 查询specList
     */
    @RequestMapping("/listSpec")
    public List<Map> listSpec(Long id){
        return typeTemplateService.listSpecification(id);
    }
}
