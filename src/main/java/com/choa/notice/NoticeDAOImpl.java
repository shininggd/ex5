package com.choa.notice;


import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.ListInfo;
import com.choa.util.RowMaker;

@Repository
//NoticeDAO noticDao = new NoticeDAO();
public class NoticeDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	private static final String NAMESPACE="NoticeMapper.";
	
		
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/
	
	@Override
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+"list", listInfo);
	}
	@Override
	public BoardDTO boardView(int num) throws Exception {
		BoardDTO boardDTO=sqlSession.selectOne(NAMESPACE+"view", num);
		return boardDTO;
	}
	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"write", boardDTO);
	}
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		
		return sqlSession.update(NAMESPACE+"update", boardDTO);
	}
	@Override
	public int boardDelete(int num) throws Exception {
		return sqlSession.delete(NAMESPACE+"delete", num);
	}
	@Override
	public int boardCount(ListInfo listInfo) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"count", listInfo);
	}
	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	

}
