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
<h1>Zarzadzaj zadaniami</h1>
<p><a href="/Warsztaty_3/AddTaskServlet">Dodaj zadanie</a></p>
<table>
	<tr>
		<td>Nazwa zadania</td>
		<td>Tresc</td>
		<td>Data dodania</td>
	</tr>
	<%
	List<String[]> tasks = (List<String[]>)request.getAttribute("tasks");
	%>
	<c:forEach var="task" items="<%= tasks%>">
	<tr>
		<td>${task[0]}</td>
		<td>${task[1]}</td>
		<td>${task[2]}</td>
		<td>${task[3]}</td>
		<td>${task[4]}</td>
	</tr>
	</c:forEach>
	
</table>
</body>
</html>