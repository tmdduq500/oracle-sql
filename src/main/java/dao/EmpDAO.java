package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {
	// exWhereIn.jsp
	public static ArrayList<Emp> selectEmpListByGrade(ArrayList<Integer> ckList) throws Exception{
		ArrayList<Emp> list = new ArrayList<>();
		int ckListLength = ckList.size();
		
		Connection conn = DBHelper.getConnection();
		
		/*
		 	where grade in (1, ... ) 
		 */
		String sql = "SELECT ename, grade"
				+ " FROM emp"
				+ " WHERE grade IN(?";
		for(int i = 1; i < ckListLength; i++) {
			sql = sql + ",?";
		}
		sql = sql + ") ORDER BY grade ASC";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		for(int i = 0; i < ckListLength; i++) {
			stmt.setInt(i+1, ckList.get(i));
		}
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Emp e = new Emp();
			e.setEmpName(rs.getString("ename"));
			e.setGrade(rs.getInt("grade"));
			list.add(e);
		}
		conn.close();
		return list;
		
	}
	
	// CASE 구문 연습 메서드
	public static ArrayList<HashMap<String , String>> selectJobCaseList() throws Exception{
		/*
		 	SELECT ename,
				job,
				CASE
				WHEN job = 'PRESIDENT' THEN '빨강'
				WHEN job = 'MANAGER' THEN '주황'
				WHEN job = 'ANALYST' THEN '노랑'
				WHEN job = 'CLERK' THEN '초록'
				ELSE '파랑' END color
			FROM emp
			ORDER BY (CASE
				WHEN color = '빨강' THEN 1
				WHEN color = '주황' THEN 2
				WHEN color = '노랑' THEN 3
				WHEN color = '초록' THEN 4
				ELSE 5 END) ASC;
		
		 */
		
		ArrayList<HashMap<String , String>> list = new ArrayList<HashMap<String , String>>();
		
		Connection conn = DBHelper.getConnection();
		
		String sql = "SELECT ename, job, CASE"
				+ " WHEN job = 'PRESIDENT' THEN '빨강'"
				+ " WHEN job = 'MANAGER' THEN '주황'"
				+ " WHEN job = 'ANALYST' THEN '노랑'"
				+ " WHEN job = 'CLERK' THEN '초록'"
				+ " ELSE '파랑' END color"
				+ " FROM emp"
				+ " ORDER BY (CASE"
				+ " WHEN color = '빨강' THEN 1"
				+ " WHEN color = '주황' THEN 2"
				+ " WHEN color = '노랑' THEN 3"
				+ " WHEN color = '초록' THEN 4"
				+ " ELSE 5 END) ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			HashMap<String, String> m = new HashMap<>();
			m.put("ename", rs.getString("ename"));
			m.put("job", rs.getString("job"));
			m.put("color", rs.getString("color"));
			list.add(m);
		}
		
		conn.close();		
		return list;
	}
	
	// DEPTNO 뒤에 부서별 인원 같이 조회하는 메서드
	public static ArrayList<HashMap<String, Integer>> selectDeptCntList() throws Exception{
		ArrayList<HashMap<String, Integer>> list = new ArrayList<HashMap<String, Integer>>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT deptno, COUNT(*) cnt"
				+ " FROM emp"
				+ " WHERE deptno IS NOT NULL"
				+ " GROUP BY deptno"
				+ " ORDER BY deptno ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			HashMap<String, Integer> m = new HashMap<>();
			m.put("deptNo", rs.getInt("deptno"));
			m.put("cnt", rs.getInt("cnt"));
			list.add(m);
			
		}
		
		conn.close();
		return list;
	}
	
	// 중복을 제거한 DEPTNO 목록을 출력하는 메서드
	public static ArrayList<Integer> selectDeptNoList() throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT DISTINCT deptno deptNo"
				+ " FROM emp"
				+ " WHERE deptno IS NOT NULL"
				+ " ORDER BY deptno ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Integer i = rs.getInt("deptNo");	// 래퍼타입과 기본타입간 호환
			list.add(i);
		}
		
		conn.close();	
		return list;
	}
	
	// 조인으로 Map 사용
	public static ArrayList<HashMap<String , Object>> selectEmpAndDeptList() throws Exception {
		ArrayList<HashMap<String , Object>> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT emp.empno empNo, emp.ename empName, emp.deptno deptNo, dept.dname deptName "
				+ "FROM emp INNER JOIN dept "
				+ "ON emp.deptno = dept.deptno";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("empName", rs.getString("empName"));
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("deptName", rs.getString("deptName"));
			list.add(m);
		}
		
		return list;
	}
	
	// VO 사용
	public static ArrayList<Emp> selectEmpList() throws Exception{
		ArrayList<Emp> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT empno empNo, ename empName, sal FROM emp";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Emp e = new Emp();
			e.setEmpNo(rs.getInt("empNo"));
			e.setEmpName(rs.getString("empName"));
			e.setSal(rs.getDouble("sal"));
			list.add(e);
		}
		return list;
	}
}
