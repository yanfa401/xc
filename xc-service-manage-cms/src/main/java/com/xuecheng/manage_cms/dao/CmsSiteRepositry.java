package com.xuecheng.manage_cms.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xuecheng.framework.domain.cms.CmsSite;

/**
* @author 谢磊
* @version 创建时间：2019年1月26日 下午8:57:45
* 类说明
*/
public interface CmsSiteRepositry extends MongoRepository<CmsSite, String>{

}
