package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbAreas;
import com.pinyougou.sellergoods.service.AreasService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/areas")
public class AreasController {

    @Reference
    private AreasService areasService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param areas
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbAreas areas, Integer pageNum, Integer pageSize){
        return areasService.listAreasByExample(areas,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeAreas")
    public Result removeAreas(Integer[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return areasService.removeAreas(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param areas
     * @return
     */
    @RequestMapping("/updateAreas")
    public Result updateAreas(@RequestBody(required = false) TbAreas areas) {
        if (!ObjectUtils.notEmpty(areas)) {
            return new Result(false, "对象不存在");
        }
        try {
            return areasService.updateAreas(areas);
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
    public TbAreas findOne(Integer id){
        return areasService.getAreasById(id);
	}
	
    /**
     * 新增数据
     * @param areas
     * @return
     */
    @RequestMapping("/saveAreas")
    public Result saveAreas(@RequestBody(required = false) TbAreas areas){
        if (!ObjectUtils.notEmpty(areas)) {
            return new Result(false, "对象不存在");
        }
        try {
            return areasService.saveAreas(areas);
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
    public List<TbAreas> findAll(){
        return areasService.findAll();
    }
}
