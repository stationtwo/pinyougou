package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbContentCategory;
import com.pinyougou.sellergoods.service.ContentCategoryService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param contentCategory
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbContentCategory contentCategory, Integer pageNum, Integer pageSize){
        return contentCategoryService.listContentCategoryByExample(contentCategory,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeContentCategory")
    public Result removeContentCategory(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return contentCategoryService.removeContentCategory(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param contentCategory
     * @return
     */
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(@RequestBody(required = false) TbContentCategory contentCategory) {
        if (!ObjectUtils.notEmpty(contentCategory)) {
            return new Result(false, "对象不存在");
        }
        try {
            return contentCategoryService.updateContentCategory(contentCategory);
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
    public TbContentCategory findOne(Long id){
        return contentCategoryService.getContentCategoryById(id);
	}
	
    /**
     * 新增数据
     * @param contentCategory
     * @return
     */
    @RequestMapping("/saveContentCategory")
    public Result saveContentCategory(@RequestBody(required = false) TbContentCategory contentCategory){
        if (!ObjectUtils.notEmpty(contentCategory)) {
            return new Result(false, "对象不存在");
        }
        try {
            return contentCategoryService.saveContentCategory(contentCategory);
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
    public List<TbContentCategory> findAll(){
        return contentCategoryService.findAll();
    }
}
