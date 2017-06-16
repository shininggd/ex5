package com.choa.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.choa.util.MakePage;
import com.choa.util.PageMaker;
import com.choa.util.RowMaker;

@Service
//NoticeService noticeService = new NoticeService();
public class NoticeService {
	
	
	//@Qualifier("notice")//같은 타입이 여러개 있을때 하나를 넣어주는 방법 NoticeDAO.java를 notice로 줬기 때문에 가능
	@Inject
	private NoticeDAO noticeDAO;
	
	public void test(){
		System.out.println(noticeDAO);
	}
	
	//Constructor
	/*public NoticeService(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}*/
	
	//View
	public NoticeDTO noticeView(int num) throws Exception{
		return noticeDAO.noticeView(num);
	}
	
	//List
	public List<NoticeDTO> noticeList(int curPage)throws Exception{
		int totalCount = noticeDAO.noticeCount();
		PageMaker pageMaker = new PageMaker(curPage);
		MakePage makePage = pageMaker.getMakePage(totalCount);
		RowMaker rowMaker = pageMaker.getRowMaker("", "");
		return noticeDAO.noticeList(rowMaker);
	}
	
	//Write
	public int noticeWrite(NoticeDTO noticeDTO)throws Exception{
		return noticeDAO.noticeWrite(noticeDTO);
	}
	
	//Update
	public int noticeUpdate(NoticeDTO noticeDTO)throws Exception{
		return noticeDAO.noticeUpdate(noticeDTO);
	}
	
	//Delete
	public int noticeDelete(int num)throws Exception{
		return noticeDAO.noticeDelete(num);
	}


}
