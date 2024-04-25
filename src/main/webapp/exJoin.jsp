<%@page import="java.util.*"%>
<%@page import="dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// emp와 emp의 mgr 정보 SELECT메서드
	ArrayList<HashMap<String, Object>> list = EmpDAO.selectEmpAndMgrList(); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
</head>
<body>
	<h1>Emp&Mgr List</h1>
	<table border="1">
		<tr>
			<th>empNo</th>
			<th>empName</th>
			<th>empGrade</th>
			<th>mgrName</th>
			<th>mgrGrade</th>
		</tr>
		
		<%
			for(HashMap<String, Object> m : list) {
		%>
				<tr>
					<td><%=m.get("empNo") %></td>
					<td><%=m.get("empName") %></td>
					<td>
						<%
							for(int i = 0; i < (int)m.get("empGrade"); i++) {
						%>
								&#127765;
						<%
							}
						%>
					</td>
					<td><%=m.get("mgrName") %></td>
					<td>
						<%
							for(int i = 0; i < (int)m.get("mgrGrade"); i++) {
						%>
								&#11088;
						<%
							}
						%>
					</td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>