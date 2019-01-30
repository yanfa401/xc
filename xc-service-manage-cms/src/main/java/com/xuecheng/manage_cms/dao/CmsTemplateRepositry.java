package com.xuecheng.manage_cms.dao;

import java.util.List;

import javax.swing.text.html.ListView;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xuecheng.framework.domain.cms.CmsTemplate;

/**
* @author 谢磊
* @version 创建时间：2019年1月28日 上午9:32:58
* 类说明
*/
public interface CmsTemplateRepositry extends MongoRepository<CmsTemplate, String>{

	//根据站点id获取对应的模板列表
	List<CmsTemplate> findBySiteId(String siteId);
}
