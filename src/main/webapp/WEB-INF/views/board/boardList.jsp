<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--List 제목  -->
<h1>${board }</h1>


<table>
	<tr>
		<td>num</td><td>title</td><td>writer</td><td>reg_date</td><td>hit</td>
	</tr>
	<c:forEach items="${list }" var="dto">
	<tr>
	<c:catch>
	<c:forEach  begin="1" end="${dto.depth }">&nbsp;</c:forEach>
	</c:catch>
	<td>${dto.num }</td><td><a href="./${board }View?num=${dto.num }">${dto.title }</a> </td><td>${dto.writer }</td><td>${dto.reg_date }</td><td>${dto.hit }</td>
	</tr>
	</c:forEach>

</table>

<a href="${board }Write">쓰기로 간다.</a>


</body>
</html>