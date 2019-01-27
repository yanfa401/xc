package com.xuecheng.manage_cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsSiteService;

/**
* @author 谢磊
* @version 创建时间：2019年1月26日 下午9:03:25
* 类说明
*/

@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSiteControllerApi{

	@Autowired
	private CmsSiteService cmsSiteService;
	
	@Override
	@GetMapping("/findAll")
	public QueryResponseResult findAll() {
		return cmsSiteService.findAll();
	}
	
}
