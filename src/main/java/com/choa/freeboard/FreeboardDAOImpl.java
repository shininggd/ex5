package com.choa.freeboard;



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
import com.choa.notice.NoticeDTO;
import com.choa.util.DBConnector;
import com.choa.util.RowMaker;



@Repository
public class FreeboardDAOImpl implements BoardDAO {
	@Inject
	private DataSource dataSource;
	
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		FreeboardDTO freeboardDTO = null;
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from freeBoard order by num desc) N) "
				+ "where R between ? and ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, rowMaker.getStartRow());
		pst.setInt(2, rowMaker.getLastRow());
		rs = pst.executeQuery();
		while(rs.next()){
			freeboardDTO = new FreeboardDTO();
			freeboardDTO.setContents(rs.getString("contents"));
			freeboardDTO.setHit(rs.getInt("hit"));
			freeboardDTO.setNum(rs.getInt("num"));
			freeboardDTO.setTitle(rs.getString("title"));
			freeboardDTO.setWriter(rs.getString("writer"));
			freeboardDTO.setReg_date(rs.getDate("reg_date"));
			freeboardDTO.setRef(rs.getInt("ref"));
			freeboardDTO.setStep(rs.getInt("step"));
			freeboardDTO.setDepth(rs.getInt("depth"));
			System.out.println(freeboardDTO.getDepth());
			ar.add(freeboardDTO);
		}
		DBConnector.disConnect(rs, pst, con);
		
		return ar;
	
	}
	@Override
	public BoardDTO boardView(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		FreeboardDTO freeboardDTO = null;
		
		String sql = "select * from freeboard where num = ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, num);
		rs = pst.executeQuery();
		if(rs.next()){
			freeboardDTO = new FreeboardDTO();
			freeboardDTO.setTitle(rs.getString("title"));
			freeboardDTO.setNum(rs.getInt("num"));
			freeboardDTO.setWriter(rs.getString("writer"));
			freeboardDTO.setHit(rs.getInt("hit"));
			freeboardDTO.setReg_date(rs.getDate("reg_date"));
			freeboardDTO.setContents(rs.getString("contents"));
			freeboardDTO.setRef(rs.getInt("ref"));
			freeboardDTO.setStep(rs.getInt("step"));
			freeboardDTO.setDepth(rs.getInt("depth"));
			
		}
		//close
		DBConnector.disConnect(rs, pst, con);
		
		return freeboardDTO;
	}
	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st= null;
		
		int result=0;
		String sql = "insert into freeboard values(notice_seq.nextval, ?, ?, ?, 0, sysdate, notice_seq.currval, 0, 0)";
		
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
		String sql ="update freeboard set title = ?, contents = ? where num = ?";
		
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
		String sql = "delete freeboard where num = ?";
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
		String sql ="select nvl(count(num),0) from freeboard";
		
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
		
		String sql ="update freeboard set hit=hit+1 where num=?";
		
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			st.executeUpdate();
			DBConnector.disConnect(st, con);
		
		
		
	}
		
}
