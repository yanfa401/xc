package com.xuecheng.manage_cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;

/**
 * @author 谢磊
 * @version 创建时间：2019年1月23日 下午1:57:45 类说明
 */

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

	@Override
	@GetMapping("/list/{page}/{size}")
	public QueryResponseResult findList(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size,
			QueryPageRequest queryPageRequest) {
		QueryResult<CmsPage> queryResult = new QueryResult<>();
		List<CmsPage> list = new ArrayList<CmsPage>();
		CmsPage c = new CmsPage();
		c.setPageHtml("aaaaaa");
		list.add(c );
		queryResult.setList(list );
		queryResult.setTotal(list.size());
		QueryResponseResult result = new QueryResponseResult(CommonCode.SUCCESS, queryResult );

		return result;
	}

}
