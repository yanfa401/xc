package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
* @author 谢磊
* @version 创建时间：2019年1月30日 上午9:35:52
* 类说明
*/
public class ExceptionCast {

	//定义静态方法抛出自定义异常
	public static void cast(ResultCode resultCode) {
		throw new CustomException(resultCode);
	}
}
