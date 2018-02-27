package com.bdms.spark.util

class LatestRemember extends Serializable{
  
   var latest : Long = 0L
   
   def getLatest : Long =  latest

   def compareAndChangeLatest( newLatest : Long ){
     
       if( newLatest >= latest ){
         latest = newLatest
       }
     
   }
   
   

}