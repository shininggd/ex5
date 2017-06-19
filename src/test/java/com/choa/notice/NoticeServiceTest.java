package com.choa.notice;



import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;

import com.choa.ex5.MyAbstractTest;


public class NoticeServiceTest extends MyAbstractTest{

	@Inject
	private NoticeServiceImpl service;
	
	
	@Test
	public void test() throws Exception{
		
		assertNotNull(service.boardView(763));
		 
	}

}
