<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="notice${path }" method="post">
  <input type="hidden"  name="num" value="${dto.num }" > 
작성자 :<input type="text"  name="writer"${dto.writer } ><br>
제목: <input type="text" name="title" value="${dto.title }"> <br>
내용:<br> <textarea rows="20" cols="20" name="contents">${dto.contents }</textarea><br>
	<button>Write</button>
</form>

</body>
</html>
