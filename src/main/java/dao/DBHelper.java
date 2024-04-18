package dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBHelper {
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = null;
		
		// 로컬에서 properties 파일 불러오기
		FileReader fr = new FileReader("D:\\webDevExercise\\auth\\oracledbSqlStudy.properties");
		Properties prop = new Properties();
		prop.load(fr);
		
		String dbUrl = prop.getProperty("dbUrl");
		String dbUser = prop.getProperty("dbUser");
		String dbPw = prop.getProperty("dbPw");
		
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		
		System.out.println("db접속 성공");
		return conn;
	}
	
	public static void main(String[] args) throws Exception {
		DBHelper db = new DBHelper();
		Connection conn =  db.getConnection();
	}
}
