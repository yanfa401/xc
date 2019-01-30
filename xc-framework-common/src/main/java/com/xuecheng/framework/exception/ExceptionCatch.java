package com.xuecheng.framework.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;

import lombok.extern.log4j.Log4j2;

/**
* @author 谢磊
* @version 创建时间：2019年1月30日 上午9:37:13
* 类说明
*/
@ControllerAdvice
@Log4j2
public class ExceptionCatch {
	

	//定义线程安全的一个map用来保存未知异常,key为异常类型，value为异常返回的code，msg等等
	//ImmutableMap的特点的一旦创建不可改变，并且线程安全
	private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
	//使用builder来构建一个异常类型和错误代码的异常
	protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();
	
	static {
		//在这里加入一些基础的异常类型判断
		builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
		builder.put(ArithmeticException.class,CommonCode.ARITHMETIC_EXCEPTION);
	}
	
	/**
	 * 捕获自定义异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public ResponseResult customException(CustomException e) {
		log.error("捕获异常：{}\r \n excption:",e.getMessage(),e);
		ResultCode resultCode = e.getResultCode();
		ResponseResult responseResult = new ResponseResult(resultCode );
		return responseResult;
	}
	
	
	/**
	 * 捕获未知异常
	 * @param exception
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public ResponseResult exception(Exception e) {
		if(EXCEPTIONS == null) {
			EXCEPTIONS = builder.build();
		}
		final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
		if(resultCode!=null) {
			//如果从map中能取到数据
			return new ResponseResult(resultCode);
		}else {
			return new ResponseResult(CommonCode.SERVER_ERROR);
		}
	}
}
