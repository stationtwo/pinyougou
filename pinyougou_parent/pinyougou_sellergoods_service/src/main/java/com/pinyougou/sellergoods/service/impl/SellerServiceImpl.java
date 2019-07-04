package com.pinyougou.sellergoods.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.TbSellerExample;
import com.pinyougou.pojo.TbSellerExample.Criteria;
import com.pinyougou.sellergoods.service.SellerService;
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
public class SellerServiceImpl implements SellerService {

    @Autowired
    private TbSellerMapper sellerMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbSeller> findAll() {
        return sellerMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSeller> page = (Page<TbSeller>) sellerMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveSeller(TbSeller seller) {
		seller.setStatus("0");
		seller.setCreateTime(new Date());
        int count = sellerMapper.insertSelective(seller);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbSeller getSellerById(String id) {
        return sellerMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateSeller(TbSeller seller) {
        int i = sellerMapper.updateByPrimaryKeySelective(seller);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeSeller(String[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (String id : idList) {
                int i = sellerMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listSellerByExample(TbSeller seller, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbSellerExample example = new TbSellerExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(seller)) {
            			if(seller.getSellerId()!=null && seller.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+seller.getSellerId()+"%");
			}
			if(seller.getName()!=null && seller.getName().length()>0){
				criteria.andNameLike("%"+seller.getName()+"%");
			}
			if(seller.getNickName()!=null && seller.getNickName().length()>0){
				criteria.andNickNameLike("%"+seller.getNickName()+"%");
			}
			if(seller.getPassword()!=null && seller.getPassword().length()>0){
				criteria.andPasswordLike("%"+seller.getPassword()+"%");
			}
			if(seller.getEmail()!=null && seller.getEmail().length()>0){
				criteria.andEmailLike("%"+seller.getEmail()+"%");
			}
			if(seller.getMobile()!=null && seller.getMobile().length()>0){
				criteria.andMobileLike("%"+seller.getMobile()+"%");
			}
			if(seller.getTelephone()!=null && seller.getTelephone().length()>0){
				criteria.andTelephoneLike("%"+seller.getTelephone()+"%");
			}
			if(seller.getStatus()!=null && seller.getStatus().length()>0){
				criteria.andStatusLike("%"+seller.getStatus()+"%");
			}
			if(seller.getAddressDetail()!=null && seller.getAddressDetail().length()>0){
				criteria.andAddressDetailLike("%"+seller.getAddressDetail()+"%");
			}
			if(seller.getLinkmanName()!=null && seller.getLinkmanName().length()>0){
				criteria.andLinkmanNameLike("%"+seller.getLinkmanName()+"%");
			}
			if(seller.getLinkmanQq()!=null && seller.getLinkmanQq().length()>0){
				criteria.andLinkmanQqLike("%"+seller.getLinkmanQq()+"%");
			}
			if(seller.getLinkmanMobile()!=null && seller.getLinkmanMobile().length()>0){
				criteria.andLinkmanMobileLike("%"+seller.getLinkmanMobile()+"%");
			}
			if(seller.getLinkmanEmail()!=null && seller.getLinkmanEmail().length()>0){
				criteria.andLinkmanEmailLike("%"+seller.getLinkmanEmail()+"%");
			}
			if(seller.getLicenseNumber()!=null && seller.getLicenseNumber().length()>0){
				criteria.andLicenseNumberLike("%"+seller.getLicenseNumber()+"%");
			}
			if(seller.getTaxNumber()!=null && seller.getTaxNumber().length()>0){
				criteria.andTaxNumberLike("%"+seller.getTaxNumber()+"%");
			}
			if(seller.getOrgNumber()!=null && seller.getOrgNumber().length()>0){
				criteria.andOrgNumberLike("%"+seller.getOrgNumber()+"%");
			}
			if(seller.getLogoPic()!=null && seller.getLogoPic().length()>0){
				criteria.andLogoPicLike("%"+seller.getLogoPic()+"%");
			}
			if(seller.getBrief()!=null && seller.getBrief().length()>0){
				criteria.andBriefLike("%"+seller.getBrief()+"%");
			}
			if(seller.getLegalPerson()!=null && seller.getLegalPerson().length()>0){
				criteria.andLegalPersonLike("%"+seller.getLegalPerson()+"%");
			}
			if(seller.getLegalPersonCardId()!=null && seller.getLegalPersonCardId().length()>0){
				criteria.andLegalPersonCardIdLike("%"+seller.getLegalPersonCardId()+"%");
			}
			if(seller.getBankUser()!=null && seller.getBankUser().length()>0){
				criteria.andBankUserLike("%"+seller.getBankUser()+"%");
			}
			if(seller.getBankName()!=null && seller.getBankName().length()>0){
				criteria.andBankNameLike("%"+seller.getBankName()+"%");
			}

        }
        Page<TbSeller> page = (Page<TbSeller>) sellerMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }

	@Override
	public Result update(String id, String status) {
		TbSeller tbSeller = sellerMapper.selectByPrimaryKey(id);
		tbSeller.setStatus(status);
		int i = sellerMapper.updateByPrimaryKeySelective(tbSeller);
		return ResultUtils.msg(i);
	}

}
