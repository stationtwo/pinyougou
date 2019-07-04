package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbContentMapper;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentExample;
import com.pinyougou.pojo.TbContentExample.Criteria;
import com.pinyougou.sellergoods.service.ContentService;
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
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

	/**
	 * 查询全部(过时)
	 */
    @Override
    public List<TbContent> findAll() {
        return contentMapper.selectByExample(null);
    }

	/**
	 * 按分页查询(过时)
	 */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

	/**
	 * 增加
	 */
    @Override
    public Result saveContent(TbContent content) {
        int count = contentMapper.insertSelective(content);
        return ResultUtils.msg(count);
    }

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
    @Override
    public TbContent getContentById(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

	/**
	 * 修改
	 */
    @Override
    public Result updateContent(TbContent content) {
        int i = contentMapper.updateByPrimaryKeySelective(content);
        return ResultUtils.msg(i);
    }

	/**
	 * 批量删除
	 */
    @Override
    public Result removeContent(Long[] idList) {
        int count = 0;
        if (ObjectUtils.notEmpty(idList)) {
            for (Long id : idList) {
                int i = contentMapper.deleteByPrimaryKey(id);
                count += i;
            }
        }
        return ResultUtils.msg(idList, count);
    }

	/**
	*条件查询 加载所有列表
	*/
    @Override
    public PageResult listContentByExample(TbContent content, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        if (ObjectUtils.notEmpty(content)) {
            			if(content.getTitle()!=null && content.getTitle().length()>0){
				criteria.andTitleLike("%"+content.getTitle()+"%");
			}
			if(content.getUrl()!=null && content.getUrl().length()>0){
				criteria.andUrlLike("%"+content.getUrl()+"%");
			}
			if(content.getPic()!=null && content.getPic().length()>0){
				criteria.andPicLike("%"+content.getPic()+"%");
			}
			if(content.getStatus()!=null && content.getStatus().length()>0){
				criteria.andStatusLike("%"+content.getStatus()+"%");
			}

        }
        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }
}
