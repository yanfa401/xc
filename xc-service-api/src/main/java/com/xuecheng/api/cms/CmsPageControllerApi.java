package com.xuecheng.api.cms;
/**
 * @author 谢磊
 * @date 2019年1月22日 下午10:00:00
 * 
 */

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 利用Swagger进行接口文档的生成
 * 
 * @author Administrator
 *
 */
@Api(value = "cms页面管理接口", description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

	/**
	 * 页面查询
	 * 
	 * @param page
	 * @param size
	 * @param queryPageRequest
	 * @return
	 */
	@ApiOperation("分页查询页面列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
			@ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int") })
	public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

	
	@ApiOperation("添加页面")
	public CmsPageResult add(CmsPage cmsPage);
	
	
	@ApiOperation("查询页面信息")
	public CmsPageResult get(String id);
	
	
	
	@ApiOperation("修改页面信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "页面id主键", required = true, paramType = "path", dataType = "int") })
	public CmsPageResult edit(String id,CmsPage cmsPage);
	
	
	@ApiOperation("删除页面")
	public ResponseResult del(String id);
	
	
}
