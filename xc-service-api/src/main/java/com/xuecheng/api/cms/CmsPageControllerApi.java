package com.xuecheng.api.cms;
/**
 * @author 谢磊
 * @date 2019年1月22日 下午10:00:00
 * 
 */

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;

public interface CmsPageControllerApi {

	/**
	   * 页面查询
	 * @param page
	 * @param size
	 * @param queryPageRequest
	 * @return
	 */
	public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

}
