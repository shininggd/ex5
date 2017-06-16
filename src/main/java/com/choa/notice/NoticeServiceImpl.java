package com.choa.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.MakePage;
import com.choa.util.PageMaker;
import com.choa.util.RowMaker;

@Service
//NoticeService noticeService = new NoticeService();
public class NoticeServiceImpl implements BoardService {
	
	
	//@Qualifier("notice")//같은 타입이 여러개 있을때 하나를 넣어주는 방법 NoticeDAO.java를 notice로 줬기 때문에 가능
		@Inject
		private NoticeDAOImpl noticeDAO;
	
	@Override
	public List<BoardDTO> boardList(int curPage) throws Exception {
		int totalCount = noticeDAO.boardCount();
		PageMaker pageMaker = new PageMaker(curPage);
		MakePage makePage = pageMaker.getMakePage(totalCount);
		RowMaker rowMaker = pageMaker.getRowMaker("", "");
		return noticeDAO.boardList(rowMaker);
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		return noticeDAO.boardView(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return noticeDAO.boardWrite(boardDTO);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		return noticeDAO.boardUpdate(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		return noticeDAO.boardDelete(num);
	}

	public void test(){
		System.out.println(noticeDAO);
	}
	
	//Constructor
	/*public NoticeService(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}*/
	


}
