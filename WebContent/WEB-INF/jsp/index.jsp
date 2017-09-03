<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="header.jsp" %>
<body>
<table>
	<tr>
		<td>Tytul zadania</td>
		<td>Autor rozwiazania</td>
		<td>Data dodania</td>
		<td>Rozwiazanie</td>
	</tr>
	<%
	List<String[]> solutions = (List<String[]>)request.getAttribute("list");
	%>
	<c:forEach var="solution" items="<%= solutions%>">
	<tr>
		<td>${solution[0]}</td>
		<td>${solution[1]}</td>
		<td>${solution[2]}</td>
		<td>${solution[3]}</td>
	</tr>
	</c:forEach>
	
</table>

</body>
</html>