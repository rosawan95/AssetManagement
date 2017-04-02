 package com.cg.assetMgmt.util;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

import com.cg.assetMgmt.exception.AssetException;


public class JndiUtil {

				//private static final Logger mylogger=
				//Logger.getLogger(JdbcUtil.class);
	

	
		    private static final Logger mylogger=Logger.getLogger(JndiUtil.class);
			public static Properties loadProperty(){
			Properties prop = new Properties();
			InputStream in=null;
			
			try {
				in=new FileInputStream("D:\\Users\\rosawan\\workspace_luna\\assetMgmt\\oracle.properties");
				prop.load(in);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}finally{
				try{
					in.close();
					
				}catch(IOException e){
					e.printStackTrace();
					
				}
			}
			return prop;
			}
			
			public static Connection getConnection() throws AssetException{
			Connection con=null;
			Properties prop=loadProperty();
			String url=prop.getProperty("url");
			String driver=prop.getProperty("driver");
			String user=prop.getProperty("uname");
			String password=prop.getProperty("upass");
			
			
			try {
				Class.forName(driver);
				mylogger.info("driver is loaded");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try{
				con=DriverManager.getConnection(url, user, password);
				mylogger.info("connected to database");
				
			}catch(SQLException e){
			//e.printStackTrace();
				mylogger.error("Not connected");
			
			}return con;
			
			}
}


/*			public static void main(String[] args){
			JdbcUtil j=new JdbcUtil();
			//PropertyConfigurator.configure("src\\log4j.properties");
			try {
				j.getConnection();
			} catch (AssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
}*/


/*public class JndiUtil {

    private DataSource dataSource;
	public JndiUtil() throws AssetException{
			try {
				Context ctx=new InitialContext();      //Get reference to remote JNDI
				dataSource=(DataSource) ctx.lookup("java:/OracleDS01");
			} catch (NamingException e) {
				
				e.printStackTrace();
				throw new AssetException("Failed to Get JNDI context",e);
			}
		
	}
	 public Connection getConnection() throws SQLException {
     	 return dataSource.getConnection();
      }
	
	
}*/
