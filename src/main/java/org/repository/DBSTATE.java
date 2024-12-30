package org.repository;
import java.sql.*;
public class DBSTATE {
	DBConfig config= DBConfig.getInstatance();
	protected Connection conn=config.getConn();
	protected PreparedStatement stmt=config.getStatement();
	protected ResultSet rs=config.getResult();
	protected CallableStatement cstmt=config.getCallStatement() ;
	DBSTATE(){
		System.out.println(config.getConn());
	}
	
}
