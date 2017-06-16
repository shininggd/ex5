package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.choa.util.DBConnector;
import com.choa.util.RowMaker;

@Repository
//NoticeDAO noticeDao = new NoticeDAO();
public class NoticeDAO {

	@Inject
	private DataSource dataSource;
	
	
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/
	//View
	public NoticeDTO noticeView(int num) throws Exception{
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
	//List
	public List<NoticeDTO> noticeList(RowMaker rowMaker) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		NoticeDTO noticeDTO = null;
		List<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice order by num desc) N) "
				+ "where R between ? and ?";
		
		pst = con.prepareStatement(sql);
		pst.setInt(1, rowMaker.getStartRow());
		pst.setInt(2, rowMaker.getLastRow());
		rs = pst.executeQuery();
		while(rs.next()){
			noticeDTO = new NoticeDTO();
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			ar.add(noticeDTO);
		}
		DBConnector.disConnect(rs, pst, con);
		
		return ar;
		
	}
	//Write
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st= null;
		
		int result=0;
		String sql = "insert into notice values(notice_seq.nextval, ?,?,?, sysdate, 0)";
		
			st = con.prepareStatement(sql);
			st.setString(1, noticeDTO.getWriter());
			st.setString(2, noticeDTO.getTitle());
			st.setString(3, noticeDTO.getContents());
			result =st.executeUpdate();
			DBConnector.disConnect(st, con);
		
		return result;
	}
	//Update
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result=0;
		String sql ="update notice set title=?, contents=? where num=?";
		
			st = con.prepareStatement(sql);
			st.setString(1, noticeDTO.getTitle());
			st.setString(2, noticeDTO.getContents());
			st.setInt(3, noticeDTO.getNum());
			result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	//Delete
	public int noticeDelete(int num) throws Exception{
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
	public int noticeCount()throws Exception {
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
	public int noticeHit(int num)throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st= null;
		int result=0;
		String sql ="update notice set hit=hit+1 where num=?";
		
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			result = st.executeUpdate();
			DBConnector.disConnect(st, con);
		
		return result;
	}

}
