package com.choa.notice;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;


import org.junit.Test;

import com.choa.board.BoardDTO;
import com.choa.ex5.MyAbstractTest;
import com.choa.util.PageMaker;
import com.choa.util.RowMaker;



/*@RunWith(SpringJUnit4ClassRunner.class)
//루트 컨택스트 위치를 말해줘야 한다.
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//xml의 위치를 말해주되 루트 컨텍스트와 서블릿 컨택스트가 들어가야 함으로 배열형으로 쓴다.
 * 
*/
public class NoticeDAOImplTest extends MyAbstractTest {
	@Inject
	private NoticeDAOImpl noticeDAOImpl;
	
	@Test
	public void connectionTest() throws Exception{
		PageMaker maker = new PageMaker(1);
		
		List<BoardDTO> ar = noticeDAOImpl.boardList(maker.getRowMaker("", ""));
		assertNotEquals( 0,ar.size());
	}
	@Test
	public void contTest() throws Exception{
		int count = noticeDAOImpl.boardCount();
		assertNotEquals( 0,count);
		
	}
}
