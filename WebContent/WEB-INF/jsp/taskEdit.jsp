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
<form action="/Warsztaty_3/EditTaskServlet" method="post">
<label>Nazwa zadania: <input type="text" name="name" value='<%=request.getAttribute("name")%>'></label><br>
<label>Tresc: <input type="text" name="task" value='<%=request.getAttribute("task")%>'></label><br>
<input type="submit" value="Wyslij">
</form>
</body>
</html>