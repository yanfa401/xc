package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
  * @author 谢磊
  * @date 2019年1月22日 下午11:00:19
  * 
  */

@SpringBootApplication
@EntityScan(basePackages= {"com.xuecheng.framework.domain"})
@ComponentScan(basePackages= {"com.xuecheng.manage_cms"})
@ComponentScan(basePackages= {"com.xuecheng.api"})
public class ManageCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageCmsApplication.class, args);
	}
}
