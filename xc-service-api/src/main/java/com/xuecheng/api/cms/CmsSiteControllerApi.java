package com.xuecheng.api.cms;

import com.xuecheng.framework.model.response.QueryResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @author 谢磊
* @version 创建时间：2019年1月26日 下午9:08:41
* 类说明
*/

@Api(value="cms站点管理",description="cms站点管理，提供增删改查功能")
public interface CmsSiteControllerApi {

	@ApiOperation("查询站点列表")
	public QueryResponseResult findAll();
}
