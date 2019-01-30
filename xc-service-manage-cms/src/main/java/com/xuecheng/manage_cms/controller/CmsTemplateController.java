package com.xuecheng.manage_cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuecheng.api.cms.CmsTemplateControllerApi;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsTemplateService;

/**
 * @author 谢磊
 * @version 创建时间：2019年1月28日 上午9:32:19 类说明
 */
@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {

	@Autowired
	private CmsTemplateService cmsTemplateService;

	@Override
	@GetMapping("/findBySiteId/{siteId}")
	public QueryResponseResult findBySiteId(@PathVariable(value = "siteId") String siteId) {
		return cmsTemplateService.findBySiteId(siteId);
	}

	@Override
	@GetMapping("/findById/{id}")
	public CmsTemplate findById(@PathVariable(value = "id") String id) {
		return cmsTemplateService.findById(id);
	}

}
