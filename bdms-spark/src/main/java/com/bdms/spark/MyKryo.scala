package com.bdms.spark

import org.apache.spark.serializer.KryoRegistrator
import com.esotericsoftware.kryo.Kryo
import akka.actor.ActorCell

class MyKryo extends KryoRegistrator {
 
	override def registerClasses(kryo: Kryo) {
		//kryo.register(classOf[PutDataActor])
		kryo.register(classOf[TestMemcache])
	}
	
	

}