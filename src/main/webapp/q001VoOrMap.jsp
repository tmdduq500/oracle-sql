<%@page import="java.util.HashMap"%>
<%@page import="vo.Emp"%>
<%@page import="dao.EmpDAO"%>
<%@page import="dao.DeptDAO"%>
<%@page import="vo.Dept"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Controller -->
<%
	// DAO를 사용 -> model 값 가져오기
	ArrayList<Dept> deptList = DeptDAO.selectDeptList();
	
	ArrayList<Emp> empList = EmpDAO.selectEmpList();
	
	ArrayList<HashMap<String, Object>> DeptOnOffList = DeptDAO.selectDeptOnOffList();
	
	ArrayList<HashMap<String, Object>> EmpAndDeptList = EmpDAO.selectEmpAndDeptList();
%>

<!-- View -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>q001VoOrMap</title>
</head>
<body>
	<h1>Dept list</h1>
	<table border="1">
		<tr>
			<th>deptNo</th>
			<th>deptName</th>
			<th>loc</th>
		</tr>
		<%
			for(Dept d : deptList) {
		%>
				<tr>
					<td><%=d.deptNo %></td>
					<td><%=d.deptName %></td>
					<td><%=d.loc%></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>Emp list</h1>
	<table border="1">
		<tr>
			<th>empNo</th>
			<th>empName</th>
			<th>sal</th>
		</tr>
		<%
			for(Emp e : empList) {
		%>
				<tr>
					<td><%=e.empNo %></td>
					<td><%=e.empName %></td>
					<td><%=e.sal%></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>Dept + onOff list</h1>
	<table border="1">
		<tr>
			<th>deptNo</th>
			<th>deptName</th>
			<th>loc</th>
			<th>OnOFF</th>
		</tr>
		<%
			for(HashMap<String, Object> don : DeptOnOffList) {
		%>
				<tr>
					<td><%=(Integer)don.get("deptNo") %></td>
					<td><%=(String)don.get("deptName") %></td>
					<td><%=(String)don.get("loc") %></td>
					<td><%=(String)don.get("onOff") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>Emp INNER JOIN Dept list</h1>
	<table border="1">
		<tr>
			<th>empNo</th>
			<th>empName</th>
			<th>deptNo</th>
			<th>deptName</th>
		</tr>
		<%
			for(HashMap<String, Object> ed : EmpAndDeptList) {
		%>
				<tr>
					<td><%=(Integer)ed.get("empNo") %></td>
					<td><%=(String)ed.get("empName") %></td>
					<td><%=(Integer)ed.get("deptNo") %></td>
					<td><%=(String)ed.get("deptName") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
</body>
</html>