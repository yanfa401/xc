package com.xuecheng.manage_cms.service;

import java.util.Optional;

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
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
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
	 * 
	 * @param cmsPage
	 * @return
	 */
	public CmsPageResult add(CmsPage cmsPage) {
		// 判断3个参数是否存在
		if (StringUtils.isBlank(cmsPage.getPageName()) || StringUtils.isBlank(cmsPage.getPageWebPath())
				|| StringUtils.isBlank(cmsPage.getSiteId())) {
			return new CmsPageResult(CommonCode.INVALID_PARAM, null);
		}
		// 根据pageName，pageWebPath，siteId作为组合组件，查询是否存在，如果存在则不能新增
		CmsPage cmspage1 = cmsPageRepositry.findByPageNameAndPageWebPathAndSiteId(cmsPage.getPageName(),
				cmsPage.getPageWebPath(), cmsPage.getSiteId());
		
		if (cmspage1 != null) {
			//如果页面已经存在
			ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
		}
		
		cmsPage.setPageId(null);
		CmsPage result = cmsPageRepositry.save(cmsPage);
		return new CmsPageResult(CommonCode.SUCCESS, result);
		
	}

	/**
	 * 根据id获取页面详情
	 * 
	 * @param id
	 * @return
	 */
	public CmsPageResult get(String id) {
		CmsPage cmsPage = getCmsPageById(id);
		if (cmsPage == null) {
			return new CmsPageResult(CommonCode.FAIL, cmsPage);
		}
		return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
	}

	private CmsPage getCmsPageById(String id) {
		CmsPage cmsPage = null;
		Optional<CmsPage> cmspageOptional = cmsPageRepositry.findById(id);
		if (cmspageOptional.isPresent()) {
			cmsPage = cmspageOptional.get();
		}
		return cmsPage;
	}

	/**
	 * 修改cmspage信息
	 * 
	 * @param id
	 *            主键id
	 * @param cmsPage
	 *            需要修改的内容
	 * @return
	 */
	public CmsPageResult edit(String id, CmsPage cmsPage) {
		// 获取当前需要修改的数据对象
		CmsPage currentCmsPage = getCmsPageById(id);
		if (currentCmsPage != null) {
			// 更新模板id
			currentCmsPage.setTemplateId(cmsPage.getTemplateId());
			// 更新所属站点
			currentCmsPage.setSiteId(cmsPage.getSiteId());
			// 更新页面别名
			currentCmsPage.setPageAliase(cmsPage.getPageAliase());
			// 更新页面名称
			currentCmsPage.setPageName(cmsPage.getPageName());
			// 更新访问路径
			currentCmsPage.setPageWebPath(cmsPage.getPageWebPath());
			// 更新物理路径
			currentCmsPage.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
			// 执行更新操作
			CmsPage result = cmsPageRepositry.save(currentCmsPage);
			if (result != null) {
				return new CmsPageResult(CommonCode.SUCCESS, result);
			}

		}
		return new CmsPageResult(CommonCode.FAIL, null);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public ResponseResult del(String id) {
		Optional<CmsPage> optional = cmsPageRepositry.findById(id);
		if(optional.isPresent()) {
			//如果存在，则删除
			cmsPageRepositry.deleteById(id);
			return new ResponseResult(CommonCode.SUCCESS);
		}
		return new ResponseResult(CommonCode.FAIL);
	}
}
