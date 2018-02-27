package com.bdms.spark.cassandra

import org.apache.log4j.Logger
import org.apache.commons.lang.StringUtils
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement
import java.sql.ResultSet
import java.sql.PreparedStatement

class CQLServer extends Serializable{
  
  private final val LOG = Logger.getLogger(classOf[CQLServer])
  
  private final val DRIVER = "org.apache.cassandra.cql.jdbc.CassandraDriver"
  
  private var conn:Connection = null;
  private var stat:PreparedStatement = null
  private var resultSet:ResultSet = null
  
  def this(hostName:String,port:String,keySpaceName:String){
    
	  this
	  if(StringUtils.isBlank(hostName)){
		  LOG.error("hostName 不能为空！")
		  System.exit(-1)
	  }
	  if(StringUtils.isBlank(port)){
		   LOG.error("port 不能为空！")
		  System.exit(-1)
	  }
	  if(StringUtils.isBlank(keySpaceName)){
		  LOG.error("keySpaceName 不能为空！")
		  System.exit(-1)
	  }
	  
	  try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection("jdbc:cassandra://" + hostName + ":" + port + "/"+ keySpaceName);
		} catch{
		  	case  t:ClassNotFoundException => LOG.error(DRIVER + " 不存在。", t)
		  	case  t:SQLException => LOG.error("获取Cassandra连接失败。", t)
		} 
    
  }
  
  
  /**
   *  return -500  executeUpdate fail
   */
  def executeUpdate(cql : String):Int = {
    
    var n:Int = 0;
    try {
	    stat = conn.prepareStatement(cql)
	    n =  stat.executeUpdate()
	   } catch{
		  	case  t:SQLException => n = -500 ;LOG.error("executeUpdate操作失败。", t)
	   } 
	   n
    
  }
  
  def excuteQuery(cql:String):ResultSet = {
    try {
	    stat = conn.prepareStatement(cql)
	    resultSet =  stat.executeQuery()
    } catch{
		  	case  t:SQLException => LOG.error("查询操作失败。", t)
	  } 
    resultSet
  }
  
  def getConnection():Connection =  conn
    
 
  
  
  def closeAll(){
    
	  try {
		  if(conn != null){
			  conn.close()
			  conn = null
		  }
		  if(stat != null){
			  stat.close()
			  stat = null
		  }
		  if(resultSet != null){
			  resultSet.close()
			  resultSet = null
		  }
	  	 } catch{
		  	case  t:SQLException => LOG.error("关闭Cassandra连接失败。", t)
		} 
  }

  
  def createKeySpace(name:String ,strategy:String ,replication_factor:String):Boolean = {
		
	val cql = "CREATE KEYSPACE " + name +" WITH REPLICATION = { 'class' :'"  + strategy +"','replication_factor': " + replication_factor + " }"
	executeUpdateForBoolean(cql)
		
	}
	
	def deleteKeySpace(name:String ):Boolean = {
		
		val cql = "DROP KEYSPACE " + name
		executeUpdateForBoolean(cql)
	}
  
	private[cassandra] def executeUpdateForBoolean(cql:String) : Boolean ={
	  
	  var result:Boolean = false
		
	  if( executeUpdate(cql) != -500){
		  result = true
	  }
	 result;
	  
	}
}