package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbProvinces;
import com.pinyougou.sellergoods.service.ProvincesService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provinces")
public class ProvincesController {

    @Reference
    private ProvincesService provincesService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param provinces
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbProvinces provinces, Integer pageNum, Integer pageSize){
        return provincesService.listProvincesByExample(provinces,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeProvinces")
    public Result removeProvinces(Integer[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return provincesService.removeProvinces(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param provinces
     * @return
     */
    @RequestMapping("/updateProvinces")
    public Result updateProvinces(@RequestBody(required = false) TbProvinces provinces) {
        if (!ObjectUtils.notEmpty(provinces)) {
            return new Result(false, "对象不存在");
        }
        try {
            return provincesService.updateProvinces(provinces);
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
    public TbProvinces findOne(Integer id){
        return provincesService.getProvincesById(id);
	}
	
    /**
     * 新增数据
     * @param provinces
     * @return
     */
    @RequestMapping("/saveProvinces")
    public Result saveProvinces(@RequestBody(required = false) TbProvinces provinces){
        if (!ObjectUtils.notEmpty(provinces)) {
            return new Result(false, "对象不存在");
        }
        try {
            return provincesService.saveProvinces(provinces);
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
    public List<TbProvinces> findAll(){
        return provincesService.findAll();
    }
}
