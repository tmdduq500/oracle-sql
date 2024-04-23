<%@page import="java.util.ArrayList"%>
<%@page import="vo.Emp"%>
<%@page import="dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	// grade에 해당하는 emp ArrayList
	ArrayList<Emp> empList = null;	

	// 요청 값
	String[] ck = request.getParameterValues("ck");

	// 요청 값 디버깅
	if(ck == null) {
		System.out.println("exWhereIn - ck = " + ck);
	} else {
		System.out.println("exWhereIn - ck.length = " + ck.length);	
		
		// ck가 String배열이므로 selectEmpListByGrade메서드의 매개변수 ArrayList<Integer>로 변환
		ArrayList<Integer> iList = new ArrayList<>();
		for(String s : ck) {
			iList.add(Integer.parseInt(s));
		}
		
		empList = EmpDAO.selectEmpListByGrade(iList);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>EMP GRADE 검색</h1>
	<form action="./exWhereIn.jsp" method="post">
		GRADE :
		<%
        	for(int i=1; i<6; i=i+1) {
      	%>
				<input type="checkbox" name="ck" value="<%=i%>"><%=i%>
		<%      
        	}
     	 %>
		<br>
		<button type="submit">검색</button>
	</form>

	<hr>
	
	<!-- 결과 -->
	<h1>결과 View</h1>
	<% 
		// ck가 null이면
		if(ck == null) {
			return ;	// 아무것도 출력 안하고 끝내기
		}
	%>
	<table border="1">
		<tr>
			<th>empName</th>
			<th>grade</th>
		</tr>
		<%
			for(Emp e : empList) {
		%>
				<tr>
					<td><%=e.getEmpName()%></td>
					<td><%=e.getGrade()%></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>