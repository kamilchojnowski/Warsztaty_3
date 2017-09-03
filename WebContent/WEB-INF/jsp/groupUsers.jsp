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
<%String name = (String)request.getAttribute("name"); %>
<p>Grupa: ${name}</p>
<table>
	<tr>
		<td>Id</td>
		<td>Nazwa</td>
		<td>Do uzytkownika</td>
	</tr>
	<%
	List<String[]> users = (List<String[]>)request.getAttribute("list");
	%>
	<c:forEach var="user" items="<%= users%>">
	<tr>
		<td>${user[0]}</td>
		<td>${user[1]}</td>
		<td>${user[2]}</td>
	</tr>
	</c:forEach>
	
</table>
</body>
</html>