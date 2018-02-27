package com.bdms.kafka.enums;

public enum KafkaPropertyEnum {
	
	TOPIC{

		public String toString() {
			return "topic";
		}
		
	},
	THREADS_NUM{

		public String toString() {
			return "threads_num";
		}
		
	},
	GAP{

		public String toString() {
			return "gap";
		}
		
	},
	ID_RANDOM{
		
		public String toString() {
			return "id_random";
		}
		
	},
	MAXRECORDCACHE{
		
		public String toString() {
			return "maxrecordcache";
		}
		
	},
	INTERVAL{

		public String toString() {
			return "interval";
		}
		
	}

}
