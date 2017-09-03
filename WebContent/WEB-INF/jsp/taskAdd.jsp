<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="header.jsp" %>
<body>
<form action="/Warsztaty_3/AddTaskServlet" method="post">
<label>Nazwa zadania: <input type="text" name="name"></label><br>
<label>Tresc: <input type="text" name="task"></label><br>
<input type="submit" value="Wyslij">
</form>
</body>
</html>