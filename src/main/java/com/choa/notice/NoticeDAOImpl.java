package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.DBConnector;
import com.choa.util.RowMaker;

@Repository
//NoticeDAO noticeDao = new NoticeDAO();
public class NoticeDAOImpl implements BoardDAO {

	@Inject
	private DataSource dataSource;
	
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		NoticeDTO boardDTO = null;
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice order by num desc) N) "
				+ "where R between ? and ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, rowMaker.getStartRow());
		pst.setInt(2, rowMaker.getLastRow());
		rs = pst.executeQuery();
		while(rs.next()){
			boardDTO = new NoticeDTO();
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setHit(rs.getInt("hit"));
			boardDTO.setNum(rs.getInt("num"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setReg_date(rs.getDate("reg_date"));
			ar.add(boardDTO);
		}
		DBConnector.disConnect(rs, pst, con);
		
		return ar;
	
	}
	@Override
	public BoardDTO boardView(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		NoticeDTO noticeDTO = new NoticeDTO();
		
		String sql = "select * from notice where num = ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, num);
		rs = pst.executeQuery();
		if(rs.next()){
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			
		}
		//close
		DBConnector.disConnect(rs, pst, con);
		
		return noticeDTO;
	}
	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st= null;
		
		int result=0;
		String sql = "insert into notice values(notice_seq.nextval, ?,?,?, sysdate, 0)";
		
			st = con.prepareStatement(sql);
			st.setString(1, boardDTO.getWriter());
			st.setString(2, boardDTO.getTitle());
			st.setString(3, boardDTO.getContents());
			result =st.executeUpdate();
			DBConnector.disConnect(st, con);
		
		return result;
	}
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result=0;
		String sql ="update notice set title=?, contents=? where num=?";
		
			st = con.prepareStatement(sql);
			st.setString(1, boardDTO.getTitle());
			st.setString(2, boardDTO.getContents());
			st.setInt(3, boardDTO.getNum());
			result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	@Override
	public int boardDelete(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st= null;
		int result=0;
		String sql ="delete notice where num=?";
		st= con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		
		return result;
		
	}
	@Override
	public int boardCount() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result=0;
		String sql ="select nvl(count(num),0) from notice";
		
			pst= con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			rs.next();
			result = rs.getInt(1);
		
			DBConnector.disConnect(rs, pst, con);
		
		return result;
	}
	@Override
	public void boardHit(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st= null;
		
		String sql ="update notice set hit=hit+1 where num=?";
		
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			st.executeUpdate();
			DBConnector.disConnect(st, con);
		
		
		
	}
	

}
