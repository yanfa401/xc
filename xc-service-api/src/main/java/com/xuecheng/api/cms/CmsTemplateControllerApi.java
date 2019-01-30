package com.xuecheng.api.cms;


import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.QueryResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @author 谢磊
* @version 创建时间：2019年1月28日 上午9:23:47
* 类说明
*/
@Api(value="cms模板管理",description="cms模板管理，提供增删改查功能")
public interface CmsTemplateControllerApi{

	
	@ApiOperation("根据站点id动态筛选模板列表")
	QueryResponseResult findBySiteId(String siteId);
	
	@ApiOperation("根据模板id获取模板详情")
	CmsTemplate findById(String id);
}
