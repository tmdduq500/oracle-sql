package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Emp;

public class EmpDAO {

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
