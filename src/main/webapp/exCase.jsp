<%@page import="java.util.*"%>
<%@page import="dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<HashMap<String , String>> list = EmpDAO.selectJobCaseList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
</head>
<body>
	<h1>Case 구문 연습</h1>
	<table border="1">
		<thead>
			<tr>
				<th>eName</th>
				<th>job</th>
				<th>color</th>
			</tr>
		</thead>
		
		<tbody>
			<%
				for(HashMap<String, String> m : list) {
			%>
					<tr>
						<td><%=m.get("ename") %></td>
						<td><%=m.get("job") %></td>
						<td><%=m.get("color") %></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
	
	
</body>
</html>