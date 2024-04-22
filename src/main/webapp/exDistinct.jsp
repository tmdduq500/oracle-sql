<%@page import="java.util.*"%>
<%@page import="dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 부서번호 리스트(중복 x)
	ArrayList<Integer> deptNoList =EmpDAO.selectDeptNoList();

	// 부서별 인원수(group by 사용)
	ArrayList<HashMap<String, Integer>> deptCntList = EmpDAO.selectDeptCntList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
</head>
<body>
	<select name="deptNo">
		<option value="">::: 선택 :::</option>
		<%
			for(int i : deptNoList) {
		%>
				<option value="<%=i%>"><%=i%></option>
		<%
			}
		%>
	</select>
	<br><br>
	
	<!-- DISTINCT 대신 GROUP BY를 사용해야하는 경우 -->
	<table border="1">
		<thead>
			<tr>
				<th>cnt</th>
				<th>deptNo</th>
			</tr>
		</thead>
		
		<tbody>
			<%
				for(HashMap<String, Integer> m : deptCntList) {
			%>
					<tr>
						<td><%=m.get("cnt") %></td>
						<td><%=m.get("deptNo") %></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>