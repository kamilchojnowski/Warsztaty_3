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
<h1>Zarzadzaj grupami</h1>
<p><a href="/Warsztaty_3/AddGroupServlet">Dodaj grupe</a></p>
<table>
	<tr>
		<td>Nazwa grupy</td>
	</tr>
	<%
	List<String[]> groups = (List<String[]>)request.getAttribute("groups");
	%>
	<c:forEach var="group" items="<%= groups%>">
	<tr>
		<td>${group[0]}</td>
		<td>${group[1]}</td>
		<td>${group[2]}</td>
		<td>${group[3]}</td>
	</tr>
	</c:forEach>
	
</table>
</body>
</html>