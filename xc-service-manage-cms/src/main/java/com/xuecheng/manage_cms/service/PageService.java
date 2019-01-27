package com.xuecheng.manage_cms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
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

	/**
	 * 条件查询CmsPage列表
	 * 
	 * @param page
	 * @param size
	 * @param queryPageRequest
	 * @return
	 */
	public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
		if (queryPageRequest == null) {
			queryPageRequest = new QueryPageRequest();
		}
		// 组装查询对象
		CmsPage cmsPageQo = new CmsPage();
		// 站点ID
		if (StringUtils.isNotBlank(queryPageRequest.getSiteId())) {
			cmsPageQo.setSiteId(queryPageRequest.getSiteId());
		}
		// 页面别名
		if (StringUtils.isNotBlank(queryPageRequest.getPageAlias())) {
			cmsPageQo.setPageAliase(queryPageRequest.getPageAlias());
		}
		// 页面别名模糊匹配
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("pageAliase",
				ExampleMatcher.GenericPropertyMatchers.contains());
		Example<CmsPage> example = Example.of(cmsPageQo, matcher);
		if (page < 1) {
			page = 1;
		}
		if (size < 10) {
			size = 10;
		}
		// 页面页码从1开始，但是在程序里是从0开始
		page = page - 1;
		Pageable pageable = PageRequest.of(page, size);
		Page<CmsPage> all = cmsPageRepositry.findAll(example, pageable);
		QueryResult<CmsPage> queryResult = new QueryResult<>();
		queryResult.setList(all.getContent());
		queryResult.setTotal(all.getTotalElements());
		QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
		return queryResponseResult;
	}

	/**
	 * 新增页面
	 * @param cmsPage
	 * @return
	 */
	public CmsPageResult add(CmsPage cmsPage) {
		// 判断3个参数是否存在
		if (StringUtils.isBlank(cmsPage.getPageName()) || StringUtils.isBlank(cmsPage.getPageWebPath())
				|| StringUtils.isBlank(cmsPage.getSiteId())) {
			return new CmsPageResult(CommonCode.FAIL, null);
		}
		// 根据pageName，pageWebPath，siteId作为组合组件，查询是否存在，如果存在则不能新增
		CmsPage cmspage1 = cmsPageRepositry.findByPageNameAndPageWebPathAndSiteId(cmsPage.getPageName(),
				cmsPage.getPageWebPath(), cmsPage.getSiteId());
		if(cmspage1==null) {
			cmsPage.setPageId(null);
			CmsPage result = cmsPageRepositry.save(cmsPage);
			return new CmsPageResult(CommonCode.SUCCESS, result);
		}
		return new CmsPageResult(CommonCode.FAIL, null);
	}

}
