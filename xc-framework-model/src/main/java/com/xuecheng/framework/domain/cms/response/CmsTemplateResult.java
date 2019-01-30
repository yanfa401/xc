package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

import lombok.Data;

/**
* @author 谢磊
* @version 创建时间：2019年1月28日 上午9:27:33
* 类说明
*/

@Data
public class CmsTemplateResult extends ResponseResult{

	CmsTemplate cmsTemplate;
	
	public CmsTemplateResult(ResultCode code,CmsTemplate cmsTemplate) {
		super(code);
		this.cmsTemplate = cmsTemplate;
	}
}
