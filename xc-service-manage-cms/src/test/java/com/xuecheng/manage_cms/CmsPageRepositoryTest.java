package com.xuecheng.manage_cms;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.MatcherConfigurer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.dao.CmsPageRepositry;

import lombok.extern.java.Log;

/**
* @author 谢磊
* @version 创建时间：2019年1月23日 下午4:09:27
* 类说明
*/

@Log
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
	
	@Autowired
	private CmsPageRepositry cmsPageRepositry;

	@Test
	public void testFindPage() {
		Pageable pageable = PageRequest.of(1, 5);
		Page<CmsPage> page = cmsPageRepositry.findAll(pageable);
		System.out.println(page.getContent());
	}
	
	@Test
	public void testUpdate() {
		Optional<CmsPage> cmspageOptional = cmsPageRepositry.findById("11");
		if(cmspageOptional.isPresent()) {
			CmsPage entity = cmspageOptional.get();
			entity.setPageAliase("abc");
			CmsPage save = cmsPageRepositry.save(entity);
			System.out.println(">>>>>>>>"+save);
		}else {
			System.out.println(">>>>>>>>>> 不存在");
		}
		
	}
	
	@Test
	public void testMyInterface() {
		CmsPage page = cmsPageRepositry.findByPageName("index.html");
		System.out.println(">>>>>>>>"+page);
	}
	
	@Test
	public void testFindByExample() {
		CmsPage cmsPage = new CmsPage();
		cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
		cmsPage.setTemplateId("5a962bf8b00ffc514038fafa");
		cmsPage.setPageAliase("轮播");
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<CmsPage> example = Example.of(cmsPage, matcher );
//		Example<CmsPage> example = Example.of(cmsPage);
		Pageable pageable = PageRequest.of(0, 10);
		Page<CmsPage> findAll = cmsPageRepositry.findAll(example , pageable);
		System.out.println(findAll.getSize()+">>>>>>>>>"+findAll.getContent());
	}
}
