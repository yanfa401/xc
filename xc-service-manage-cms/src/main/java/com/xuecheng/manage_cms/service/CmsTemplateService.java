package com.xuecheng.manage_cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepositry;

/**
* @author 谢磊
* @version 创建时间：2019年1月28日 上午9:34:26
* 类说明
*/

@Service
public class CmsTemplateService {

	@Autowired
	private CmsTemplateRepositry cmsTemplateRepositry;
	
	/**
	 * 根据站点id获取模板列表
	 * @param siteId
	 * @return
	 */
	public QueryResponseResult findBySiteId(String siteId) {
		//如果传入值为空，直接返回fail
		if(StringUtils.isBlank(siteId)) {
			return new QueryResponseResult(CommonCode.FAIL, null );
		}
		List<CmsTemplate> cmsTemplates = cmsTemplateRepositry.findBySiteId(siteId);
		if(cmsTemplates ==  null) {
			cmsTemplates = new ArrayList<>();
		}
		QueryResult<CmsTemplate> queryResult = new QueryResult<>();
		queryResult.setList(cmsTemplates);
		queryResult.setTotal(cmsTemplates.size());
		QueryResponseResult result = new QueryResponseResult(CommonCode.SUCCESS, queryResult );
		return result;
	}
	
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @return
	 */
	public CmsTemplate findById(String id) {
		Optional<CmsTemplate> optional = cmsTemplateRepositry.findById(id);
		if(optional.isPresent()) {
			CmsTemplate cmsTemplate = optional.get();
			return cmsTemplate;
		}
		return null;
	}
}
