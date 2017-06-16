package com.choa.ex5;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//루트 컨택스트 위치를 말해줘야 한다.
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//xml의 위치를 말해주되 루트 컨텍스트와 서블릿 컨택스트가 들어가야 함으로 배열형으로 쓴다.
public abstract class MyAbstractTest {


}
