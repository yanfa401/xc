package com.xuecheng.manage_cms.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xuecheng.framework.domain.cms.CmsPage;

/**
* @author 谢磊
* @version 创建时间：2019年1月23日 下午2:32:48
* 类说明
*/
public interface CmsPageRepositry extends MongoRepository<CmsPage, String>{

}
