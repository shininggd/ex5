package com.choa.freeboard;




import java.util.List;

import javax.inject.Inject;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;

import com.choa.util.RowMaker;



@Repository
public class FreeboardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "FreeboardMapper."; //FINAL 로 인해 상수치급 하면 변수명은 대문자
	
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+"list", rowMaker);
	
	}
	@Override
	public BoardDTO boardView(int num) throws Exception {
BoardDTO boardDTO = sqlSession.selectOne(NAMESPACE+"view", num);
		
		return boardDTO;
	}
	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		int result = sqlSession.insert(NAMESPACE+"write", boardDTO);
		return result;
	}
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
int result= sqlSession.update(NAMESPACE+"update",boardDTO);
		
		return result;
	}
	@Override
	public int boardDelete(int num) throws Exception {
int result =sqlSession.delete(NAMESPACE+"delete", num);
		
		return result;
		
	}
	@Override
	public int boardCount() throws Exception {
		int result=sqlSession.selectOne(NAMESPACE+"count");		
		return result;
	}
	@Override
	public void boardHit(int num) throws Exception {
		
		
		
		
	}
		
}
