package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Dept;

public class DeptDAO {
	// VO 사용
	public static ArrayList<Dept> selectDeptList() throws Exception{
		ArrayList<Dept> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT deptno deptNo, dname deptName, loc FROM dept";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Dept d = new Dept();
			d.deptNo = rs.getInt("deptNo");
			d.deptName = rs.getString("deptName");
			d.loc = rs.getString("loc");
			list.add(d);
		}
		return list;
	}
	
	// Map 사용
	public static ArrayList<HashMap<String, Object>> selectDeptOnOffList() throws Exception{
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT deptno deptNo, dname deptName, loc, 'ON' onOff FROM dept";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("deptName", rs.getString("deptName"));
			m.put("loc", rs.getString("loc"));
			m.put("onOff", rs.getString("onOff"));
			list.add(m);
		}
		return list;
	} 
}
