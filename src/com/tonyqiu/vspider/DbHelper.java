package com.tonyqiu.vspider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ListCellRenderer;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class DbHelper {
	private static Connection conn = null;  

	 /****数据库连接*** */  
    public static Connection getConnection() {  
    	if(conn == null) {
	        String driver = "com.mysql.jdbc.Driver";  
	        String url = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8";  
	  
	        DbUtils.loadDriver(driver);  
	  
	        try {  
	            conn = DriverManager.getConnection(url, "root", "devpassword123");  
	        } catch (SQLException ex) {  
	            ex.printStackTrace();  
	        }  
    	}
        return conn;  
    } 
    
	public static void saveColumns(List<List<ContentColumn>> list) throws SQLException{
		QueryRunner run = new QueryRunner();
		String sql="INSERT into `columns` (job_id,title,value,create_by,create_time,update_by,update_time) values (job_id,?,?,NULL,now(),NULL,now())";
		List<Object[]> paramsList = new ArrayList<Object[]>();
		for(List<ContentColumn> subList : list) {
			for(ContentColumn column : subList) {
				paramsList.add(new Object[]{column.name, column.value});
				
			}
		}
		
		Object[][] params = new Object[paramsList.size()][2];
		for(int i=0; i<paramsList.size(); i++) {
			params[i] = paramsList.get(i);
		}
		
		run.batch(getConnection(), sql, params);
		
	}
}
