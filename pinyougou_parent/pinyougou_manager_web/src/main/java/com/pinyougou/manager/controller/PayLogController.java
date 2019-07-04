package com.pinyougou.manager.controller;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbPayLog;
import com.pinyougou.sellergoods.service.PayLogService;
import com.pinyougou.util.ObjectUtils;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payLog")
public class PayLogController {

    @Reference
    private PayLogService payLogService;

    /**
     * 查询所有 (包含模糊查询和分页)
     * @param payLog
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody(required = false) TbPayLog payLog, Integer pageNum, Integer pageSize){
        return payLogService.listPayLogByExample(payLog,pageNum, pageSize);
    }

    /**
     * 删除方法
     * @param idList
     * @return
     */
    @RequestMapping("/removePayLog")
    public Result removePayLog(String[] idList) {
        if (!ObjectUtils.notEmpty(idList)) {
            return new Result(false, "未选中要删除的条目");
        }
        try {
            return payLogService.removePayLog(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "未知的错误信息,请重试");
        }
    }

    /**
     * 修改对象
     * @param payLog
     * @return
     */
    @RequestMapping("/updatePayLog")
    public Result updatePayLog(@RequestBody(required = false) TbPayLog payLog) {
        if (!ObjectUtils.notEmpty(payLog)) {
            return new Result(false, "对象不存在");
        }
        try {
            return payLogService.updatePayLog(payLog);
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
    public TbPayLog findOne(String id){
        return payLogService.getPayLogById(id);
	}
	
    /**
     * 新增数据
     * @param payLog
     * @return
     */
    @RequestMapping("/savePayLog")
    public Result savePayLog(@RequestBody(required = false) TbPayLog payLog){
        if (!ObjectUtils.notEmpty(payLog)) {
            return new Result(false, "对象不存在");
        }
        try {
            return payLogService.savePayLog(payLog);
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
    public List<TbPayLog> findAll(){
        return payLogService.findAll();
    }
}
