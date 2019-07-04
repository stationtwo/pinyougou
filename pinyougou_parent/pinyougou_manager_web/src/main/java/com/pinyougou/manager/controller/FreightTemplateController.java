package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbFreightTemplate;
import com.pinyougou.sellergoods.service.FreightTemplateService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/freightTemplate")
public class FreightTemplateController {

    @Reference
    private FreightTemplateService freightTemplateService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param freightTemplate
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbFreightTemplate freightTemplate, Integer pageNum, Integer pageSize){
        return freightTemplateService.listFreightTemplateByExample(freightTemplate,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeFreightTemplate")
    public Result removeFreightTemplate(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return freightTemplateService.removeFreightTemplate(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param freightTemplate
     * @return
     */
    @RequestMapping("/updateFreightTemplate")
    public Result updateFreightTemplate(@RequestBody(required = false) TbFreightTemplate freightTemplate) {
        if (!ObjectUtils.notEmpty(freightTemplate)) {
            return new Result(false, "对象不存在");
        }
        try {
            return freightTemplateService.updateFreightTemplate(freightTemplate);
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
    public TbFreightTemplate findOne(Long id){
        return freightTemplateService.getFreightTemplateById(id);
	}
	
    /**
     * 新增数据
     * @param freightTemplate
     * @return
     */
    @RequestMapping("/saveFreightTemplate")
    public Result saveFreightTemplate(@RequestBody(required = false) TbFreightTemplate freightTemplate){
        if (!ObjectUtils.notEmpty(freightTemplate)) {
            return new Result(false, "对象不存在");
        }
        try {
            return freightTemplateService.saveFreightTemplate(freightTemplate);
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
    public List<TbFreightTemplate> findAll(){
        return freightTemplateService.findAll();
    }
}
