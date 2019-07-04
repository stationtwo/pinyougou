package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbPayLogMapper;
import com.pinyougou.pojo.TbPayLog;
import com.pinyougou.pojo.TbPayLogExample;
import com.pinyougou.pojo.TbPayLogExample.Criteria;
import com.pinyougou.sellergoods.service.PayLogService;
import com.pinyougou.util.ObjectUtils;
import com.pinyougou.util.ResultUtils;
import entity.PageResult;
import entity.Result;									  

import entity.PageResult;

/**
 * 服务实现层
 * @author jieweili
 *
 */
@Service
public class PayLogServiceImpl implements PayLogService {

    @Autowired
    private TbPayLogMapper payLogMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbPayLog> findAll() {
        return payLogMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbPayLog> page = (Page<TbPayLog>) payLogMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result savePayLog(TbPayLog payLog) {
        int count = payLogMapper.insertSelective(payLog);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbPayLog getPayLogById(String id) {
        return payLogMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updatePayLog(TbPayLog payLog) {
        int i = payLogMapper.updateByPrimaryKeySelective(payLog);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removePayLog(String[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (String id : idList) {
                int i = payLogMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listPayLogByExample(TbPayLog payLog, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbPayLogExample example = new TbPayLogExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(payLog)) {
            			if(payLog.getOutTradeNo()!=null && payLog.getOutTradeNo().length()>0){
				criteria.andOutTradeNoLike("%"+payLog.getOutTradeNo()+"%");
			}
			if(payLog.getUserId()!=null && payLog.getUserId().length()>0){
				criteria.andUserIdLike("%"+payLog.getUserId()+"%");
			}
			if(payLog.getTransactionId()!=null && payLog.getTransactionId().length()>0){
				criteria.andTransactionIdLike("%"+payLog.getTransactionId()+"%");
			}
			if(payLog.getTradeState()!=null && payLog.getTradeState().length()>0){
				criteria.andTradeStateLike("%"+payLog.getTradeState()+"%");
			}
			if(payLog.getOrderList()!=null && payLog.getOrderList().length()>0){
				criteria.andOrderListLike("%"+payLog.getOrderList()+"%");
			}
			if(payLog.getPayType()!=null && payLog.getPayType().length()>0){
				criteria.andPayTypeLike("%"+payLog.getPayType()+"%");
			}

        }
        Page<TbPayLog> page = (Page<TbPayLog>) payLogMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
