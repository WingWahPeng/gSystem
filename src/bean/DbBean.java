package bean;

import java.sql.*;
public class DbBean {

private Statement stmt=null;
private Connection conn=null;
private ResultSet rs=null;
//���캯��
public DbBean(){}

//������
public void openConnection(){
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		
		String url="jdbc:sqlserver://localhost:1433;DatabaseName=gmSystem";
		String user="sa";
		String password="a123";
		conn=DriverManager.getConnection(url,user,password);
	}catch(ClassNotFoundException e){
		System.err.println("openConn:"+e.getMessage());
	}catch(SQLException e){
		System.err.println("openConn:"+e.getMessage());
	}catch(Exception e){
		System.err.println("openConn:"+e.getMessage());
	}
}
//ִ�в�ѯ����
public ResultSet executeQuery(String sql){
	
	rs=null;
	try{
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs=stmt.executeQuery(sql);
		
	}catch(SQLException e){
		System.err.println("executeQuery:"+e.getMessage());
	}
	
	return rs;
	
}
//ִ�и���

public int executeUpdate(String sql){
	int n=0;
	try{
		stmt=conn.createStatement();
		n=stmt.executeUpdate(sql);
	}
	catch(Exception e){
		System.out.print(e.toString());
	}
	return n;
	
}
//�ر�����

public void closeConnection(){
	try{
		if(rs!=null)
			rs.close();
	}catch(SQLException e){
		System.err.println("closeRe:"+e.getMessage());
	}
	
	try{
		if(stmt!=null)
			stmt.close();
	}catch(SQLException e){
		System.err.println("closeStmt:"+e.getMessage());
	}
	
	try{
		if(conn!=null)
			conn.close();
	}catch(SQLException e){
		System.err.println("closeConn:"+e.getMessage());
	}	
}

}
