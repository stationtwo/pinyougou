package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbCities;
import com.pinyougou.sellergoods.service.CitiesService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Reference
    private CitiesService citiesService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param cities
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbCities cities, Integer pageNum, Integer pageSize){
        return citiesService.listCitiesByExample(cities,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeCities")
    public Result removeCities(Integer[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return citiesService.removeCities(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param cities
     * @return
     */
    @RequestMapping("/updateCities")
    public Result updateCities(@RequestBody(required = false) TbCities cities) {
        if (!ObjectUtils.notEmpty(cities)) {
            return new Result(false, "对象不存在");
        }
        try {
            return citiesService.updateCities(cities);
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
    public TbCities findOne(Integer id){
        return citiesService.getCitiesById(id);
	}
	
    /**
     * 新增数据
     * @param cities
     * @return
     */
    @RequestMapping("/saveCities")
    public Result saveCities(@RequestBody(required = false) TbCities cities){
        if (!ObjectUtils.notEmpty(cities)) {
            return new Result(false, "对象不存在");
        }
        try {
            return citiesService.saveCities(cities);
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
    public List<TbCities> findAll(){
        return citiesService.findAll();
    }
}
