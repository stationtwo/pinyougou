package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.sellergoods.service.ContentService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbContent content, Integer pageNum, Integer pageSize){
        return contentService.listContentByExample(content,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeContent")
    public Result removeContent(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return contentService.removeContent(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param content
     * @return
     */
    @RequestMapping("/updateContent")
    public Result updateContent(@RequestBody(required = false) TbContent content) {
        if (!ObjectUtils.notEmpty(content)) {
            return new Result(false, "对象不存在");
        }
        try {
            return contentService.updateContent(content);
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
    public TbContent findOne(Long id){
        return contentService.getContentById(id);
	}
	
    /**
     * 新增数据
     * @param content
     * @return
     */
    @RequestMapping("/saveContent")
    public Result saveContent(@RequestBody(required = false) TbContent content){
        if (!ObjectUtils.notEmpty(content)) {
            return new Result(false, "对象不存在");
        }
        try {
            return contentService.saveContent(content);
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
    public List<TbContent> findAll(){
        return contentService.findAll();
    }
}
