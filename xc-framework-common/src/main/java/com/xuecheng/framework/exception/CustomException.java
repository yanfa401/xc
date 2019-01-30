package com.xuecheng.framework.exception;
/**
* @author 谢磊
* @version 创建时间：2019年1月30日 上午9:33:09
* 类说明
*/

import com.xuecheng.framework.model.response.ResultCode;

public class CustomException extends RuntimeException{

	private ResultCode resultCode;
	
	//成员变量get方法
	public ResultCode getResultCode() {
		return this.resultCode;
	}

	public CustomException(ResultCode resultCode) {
		super("错误代码："+resultCode.code()+"错误信息："+resultCode.message());
		this.resultCode = resultCode;
	}
	
	
	
}
