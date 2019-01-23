package com.xuecheng.manage_cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepositry;

/**
 * @author 谢磊
 * @version 创建时间：2019年1月23日 下午9:58:21 类说明
 */

@Service
public class PageService {

	@Autowired
	private CmsPageRepositry cmsPageRepositry;

	public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
		if (page < 1) {
			page = 1;
		}
		if (size < 10) {
			size = 10;
		}
		// 页面页码从1开始，但是在程序里是从0开始
		page = page - 1;
		Pageable pageable = PageRequest.of(page, size);
		Page<CmsPage> all = cmsPageRepositry.findAll(pageable);
		QueryResult<CmsPage> queryResult = new QueryResult<>();
		queryResult.setList(all.getContent());
		queryResult.setTotal(all.getTotalElements());
		QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
		return queryResponseResult;
	}

}
