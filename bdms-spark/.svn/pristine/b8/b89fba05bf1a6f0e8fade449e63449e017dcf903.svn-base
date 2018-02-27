package com.bdms.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.catalyst.types.StructType
import org.apache.spark.sql.catalyst.types.StructField
import org.apache.spark.sql.catalyst.types.StringType
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types._;

object TestSparkSQL {
  
  def main(args: Array[String]) {
  
    val sparkConf = new SparkConf().setAppName("SparkTest")
    val sc = new SparkContext(sparkConf)
    
 // sc is an existing SparkContext.
	val sqlContext = new org.apache.spark.sql.SQLContext(sc)
	
	// Create an RDD
	val people = sc.textFile("examples/src/main/resources/people.txt")
	
	// The schema is encoded in a string
	val schemaString = "name age"
	
	// Import Row.
	// Import Spark SQL data types
	
	
	// Generate the schema based on the string of schema
	val schema =
	  StructType(
	    schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))
	
	// Convert records of the RDD (people) to Rows.
	val rowRDD = people.map(_.split(",")).map(p => Row(p(0), p(1).trim))
	
	// Apply the schema to the RDD.
	//val peopleDataFrame = sqlContext.createDataFrame(rowRDD, schema)
	//1.2.1
	val peopleDataFrames = sqlContext.applySchema(rowRDD, schema)
	
	// Register the DataFrames as a table.
	peopleDataFrames.registerTempTable("people")
	
	// SQL statements can be run by using the sql methods provided by sqlContext.
	val results = sqlContext.sql("SELECT name FROM people")
	
	// The results of SQL queries are DataFrames and support all the normal RDD operations.
	// The columns of a row in the result can be accessed by ordinal.
	results.map(t => "Name: " + t(0)).collect().foreach(println)
  }

}