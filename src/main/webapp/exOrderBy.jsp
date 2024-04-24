<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>

<!-- Controller -->
<%
	// 어떤 컬럼으로 정렬할건지 정하기
	String colName = request.getParameter("colName");
	System.out.println("exOrderBy.jsp - colName = " + colName);	// 디버깅
	
	// 내림차순인지 오름차순인지 정하기 : asc / desc
	String sort = request.getParameter("sort");
	System.out.println("exOrderBy.jsp - sort = " + sort);	// 디버깅
	
	// DAO 호출 -> 모델 값을 반환받기 위해
	ArrayList<Emp> list = EmpDAO.selectEmpListSort(colName, sort);
	System.out.println("exOrderBy.jsp - list.size = " + list.size());	// 디버깅
%>

<!-- View -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>
				empno
				<a href="./exOrderBy.jsp?colName=empno&sort=ASC">오름차순</a>
				<a href="./exOrderBy.jsp?colName=empno&sort=DESC">내림차순</a>
			</th>
			<th>
				ename
				<a href="./exOrderBy.jsp?colName=ename&sort=ASC">오름차순</a>
				<a href="./exOrderBy.jsp?colName=ename&sort=DESC">내림차순</a>
			</th>
		</tr>
		
		<%
			for(Emp e : list) {
		%>
				<tr>
					<td>
						<%=e.getEmpNo()%>
					</td>
					<td>
						<%=e.getEmpName()%>
					</td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>