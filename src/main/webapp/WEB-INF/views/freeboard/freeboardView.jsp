<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
번호: ${dto.num }<br> 
글쓴이: ${dto.writer }<br> 
제목: ${dto.title } <br>
날짜: ${dto.reg_date }<br>
조회수: ${dto.hit }<br>
<a href="freeboardDelete?num=${dto.num }">딜리트로 간다.</a>
<a href="freeboardUpdate?num=${dto.num }">업데이트로 간다.</a>
<a href="freeboardList">리스트로 간다.</a>
</body>
</html>