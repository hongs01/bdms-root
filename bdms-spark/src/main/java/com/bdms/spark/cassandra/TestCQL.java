package com.bdms.spark.cassandra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestCQL {
	
	private static  Connection con;
	
	public TestCQL(){
		
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			con = DriverManager.getConnection("jdbc:cassandra://DSwhHadoop-1:9160/person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) throws Exception{

		TestCQL testCQL = new TestCQL();
		//testCQL.createKeySpace("person","SimpleStrategy",1);
		
		/*String cql = "CREATE TABLE users (id varchar,simId varchar,gis_x varchar,gis_y varchar,name varchar,phoneNum varchar," +
				"address varchar,time varchar,PRIMARY KEY (id));";*/
		
		String cql = "SELECT simId,gis_x,gis_y FROM users ";
		
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = con.prepareStatement(cql);
			ResultSet res = prepareStatement.executeQuery();
			int n = 0 ;
			while(res.next()){
				n++;
				System.out.println(res.getString(1) + " -- " + res.getString(2) + " *** " + res.getString(3));
			}
			System.out.println(n);
			
			/*
			MessageData md = new MessageData();
			
			StringBuffer str = new StringBuffer();
			str.append("BEGIN BATCH  ");
			for(int i = 0 ; i < 10000 ; i++){
				md.init(i);
				str.append("INSERT INTO users (id, simId,gis_x,gis_y,name,phoneNum,address,time) VALUES (");
				str.append( "'" + i +  "',");
				str.append( "'" + md.getSimId() +  "',");
				str.append( "'" + md.getGis_x() +  "',");
				str.append( "'" + md.getGis_y() +  "',");
				str.append( "'" + md.getName() +  "',");
				str.append( "'" + md.getPhoneNoPer() +  "',");
				str.append( "'" + md.getAddress() +  "',");
				str.append( "'" + md.getTime() +  "'");
				str.append(");");
			}
			
			str.append(" APPLY BATCH");
			
			prepareStatement = con.prepareStatement(str.toString());
			prepareStatement.executeUpdate();*/
			
			System.out.println(" im ok ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				prepareStatement.close();
			} catch (SQLException e) {
			}
			prepareStatement = null;
		}

	}

	
	public boolean createKeySpace(String name,String strategy,int replication_factor) {
		
		String cql = "CREATE KEYSPACE " + name +" WITH REPLICATION = { 'class' :'"  + strategy +"','replication_factor': " + replication_factor + " }";
		
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = con.prepareStatement(cql);
			prepareStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				prepareStatement.close();
			} catch (SQLException e) {
			}
			prepareStatement = null;
		}
		
		return false;
		
	}
	
	public boolean deleteKeySpace(String name){
		
		String cql = "DROP KEYSPACE " + name;
		
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = con.prepareStatement(cql);
			prepareStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				prepareStatement.close();
			} catch (SQLException e) {
			}
			prepareStatement = null;
		}
		
		return false;
	}

	
}
