package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbAddress;
import com.pinyougou.sellergoods.service.AddressService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param address
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbAddress address, Integer pageNum, Integer pageSize){
        return addressService.listAddressByExample(address,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removeAddress")
    public Result removeAddress(Long[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return addressService.removeAddress(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param address
     * @return
     */
    @RequestMapping("/updateAddress")
    public Result updateAddress(@RequestBody(required = false) TbAddress address) {
        if (!ObjectUtils.notEmpty(address)) {
            return new Result(false, "对象不存在");
        }
        try {
            return addressService.updateAddress(address);
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
    public TbAddress findOne(Long id){
        return addressService.getAddressById(id);
	}
	
    /**
     * 新增数据
     * @param address
     * @return
     */
    @RequestMapping("/saveAddress")
    public Result saveAddress(@RequestBody(required = false) TbAddress address){
        if (!ObjectUtils.notEmpty(address)) {
            return new Result(false, "对象不存在");
        }
        try {
            return addressService.saveAddress(address);
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
    public List<TbAddress> findAll(){
        return addressService.findAll();
    }
}
