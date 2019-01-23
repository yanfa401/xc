package com.xuecheng.manage_cms;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.dao.CmsPageRepositry;

/**
* @author 谢磊
* @version 创建时间：2019年1月23日 下午4:09:27
* 类说明
*/

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
}
