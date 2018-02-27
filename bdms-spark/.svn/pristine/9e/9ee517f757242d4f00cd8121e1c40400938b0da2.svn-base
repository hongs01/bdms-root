package com.bdms.spark.wifi

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.HConnectionManager
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.hadoop.hbase.regionserver.BloomType
import com.bdms.hbse.enums.Wifi2Meta
import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.client.Result

object WifiHbaseUtil 
{
	val HBASESITE = "hbase/hbase-site.xml"
	val HBASEHDFS = "hbase/hdfs-site.xml" 
	val TABLENAME = "wifi2"
	
	
	def getHconf()=
	{
	  val hConf =HBaseConfiguration.create();
	  hConf
	}
	  
	def getConn()=
	{
		val hconf = getHconf
			hconf.addResource(HBASESITE);
			hconf.addResource(HBASEHDFS);
		val conn = HConnectionManager.createConnection(hconf);
		
		conn
	}
	
	def createTab(tabName:String,cloumnFamilyName:String)=
	{
	  val name = TableName.valueOf(tabName)
	  val conn = getConn
	  val conf = getHconf
	  if(! conn.isTableAvailable(name))
	  {
	    val admin = new HBaseAdmin(conf)
	    var hcd = new HColumnDescriptor(cloumnFamilyName)
	    hcd.setMaxVersions(1).setBloomFilterType(BloomType.ROWCOL)
	    var htd = new HTableDescriptor(name)
	    htd.addFamily(hcd)
	    admin.createTable(htd)
	    admin.close()
	  }
	  (conf,conn)
	}
	
	def getByRowKey(rowKey:String,tabName:String,familyName:String):Result=
	{
	  val conn = createTab(tabName,familyName)._2
	  val tab = conn.getTable(tabName)
	  val get = new Get(rowKey.getBytes())
	  val row = tab.get(get)
	  row
	}
	
	def main(args: Array[String]) 
	{
		val row = getByRowKey("6314226560034CBB0C361-20140328102500", "wifi2","info")
		println(row.size())
//		for ( kv <- row.raw()) 
//		{
//			//System.out.print(new String(kv.getRow()) + " ");
//			//System.out.println(new String(kv.getFamily()));
//			//System.out.println(new String(kv.getQualifier()));
//			//System.out.println(new String(kv.getValue()));
//			//System.out.print(" timestamp = " + kv.getTimestamp() + "\n");
//		}

	}
	    
}