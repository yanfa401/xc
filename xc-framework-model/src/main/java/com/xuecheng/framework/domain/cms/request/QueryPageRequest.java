package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
  * @author 谢磊
  * @date 2019年1月22日 下午9:43:23
  * 
  */

@Data
public class QueryPageRequest extends RequestData{

	//站点id
	@ApiModelProperty("站点id")
	private String siteId;
	//页面id
	@ApiModelProperty("页面id")
	private String pageId;
	//页面名称
	@ApiModelProperty("页面名称")
	private String pageName;
	//别名
	@ApiModelProperty("别名")
	private String pageAlias;
	//模板id
	@ApiModelProperty("模板id")
	private String templateId;
}
