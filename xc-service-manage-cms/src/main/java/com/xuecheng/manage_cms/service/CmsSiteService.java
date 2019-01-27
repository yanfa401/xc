package com.xuecheng.manage_cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsSiteRepositry;

/**
* @author 谢磊
* @version 创建时间：2019年1月26日 下午8:58:54
* 类说明
*/

@Service
public class CmsSiteService {

	@Autowired
	private CmsSiteRepositry cmsSiteRepositry;
	
	/**
	 * 查询所有站点列表
	 */
	public QueryResponseResult findAll() {
		List<CmsSite> cmsSiteList = cmsSiteRepositry.findAll();
		QueryResult<CmsSite> queryResult = new QueryResult<>();
		queryResult.setList(cmsSiteList);
		QueryResponseResult result = new QueryResponseResult(CommonCode.SUCCESS, queryResult );
		return result;
	}
}
