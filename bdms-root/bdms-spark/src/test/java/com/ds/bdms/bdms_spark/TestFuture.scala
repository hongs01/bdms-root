package com.ds.bdms.bdms_spark

import org.apache.spark.util.random.XORShiftRandom
import org.apache.spark.mllib.linalg.Vectors
import scala.util.Random
import org.springframework.beans.factory.annotation.Autowired
import com.bdms.dams.station.service.StationService
import org.junit.Test
import org.springframework.context.support.GenericXmlApplicationContext


object TestFuture {
  
  def main(args: Array[String]) {
     
       val context = new GenericXmlApplicationContext();
        context.setValidating(false);
        context.load("classpath:spring/spring-core-config.xml");
        context.refresh();
        val stationService = context.getBean(classOf[StationService]);
       val map = stationService.findAllTrans();
       context.close()
       
       val keys = map.keySet().iterator()
      while(keys.hasNext()){
       val key =  keys.next()
        println( key + "===" +  map.get(key));
      }
       
     
     
  }

}
