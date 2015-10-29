/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch01.dao.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ConnectionImpl;
import com.springinpractice.ch01.dao.AccountDao;
import com.springinpractice.ch01.model.Account;

public class JdbcAccountDao implements AccountDao {
	
	private DataSource dataSource;
	
	public JdbcAccountDao() {}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Account> findAll() throws Exception {
		List<Account> results = new ArrayList<Account>();
		 java.sql.Connection conn = dataSource.getConnection();
		 String sqlStmt = "Select * from account2";
		 Statement stmt = conn.prepareStatement(sqlStmt);
		 ResultSet rs = stmt.executeQuery(sqlStmt);
		 DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");	
		 while (rs.next())
		 {			 
			 String firstColumn = rs.getString(1);
			 String secondColumn = rs.getString(2);
			 String thirdColumn = rs.getString(3);
			 System.out.println("First : " + firstColumn + ", second : " + secondColumn + ", third : " + thirdColumn ); 
			 Account cpte= new Account(firstColumn,new BigDecimal(secondColumn), fmt.parse(thirdColumn));
			 results.add(cpte);
		 }		
		return results;
	}		
}
