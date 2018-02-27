package com.bdms.sqoop.ds_enum;

public enum DBType{
		
		MYSQL(){
			// default port : 3306
			public String getUrlModel(){
				return "jdbc:mysql://{host}:{port}/{db}";
			}
			public String getDriver(){
				return "com.mysql.jdbc.Driver";
			}
			public String getDefaultPort(){
				return "3306";
			}
			
		},SQLSERVER(){
			// default port : 1433
			public String getUrlModel(){
				return "jdbc:jtds:sqlserver://{host}:{port}/{db}";
			}
			public String getDriver(){
				return "net.sourceforge.jtds.jdbc.Driver";
			}
			public String getDefaultPort(){
				return "1433";
			}
			
		},ORACLE(){
			// default port : 1521
			public String getUrlModel(){
				return "jdbc:oracle:thin:@{host}:{port}:{db}";
			}
			public String getDriver(){
				return "oracle.jdbc.driver.OracleDriver";
			}
			public String getDefaultPort(){
				return "1521";
			}
			
		},DB2(){
			// default port : 5000
			public String getUrlModel(){
				return "jdbc:db2://{host}:{port}/{db}";
			}
			public String getDriver(){
				return "com.ibm.db2.jdbc.app.DB2Driver ";
			}
			public String getDefaultPort(){
				return "5000";
			}
			
		},SYBASE(){
			// default port : 5007
			public String getUrlModel(){
				return "jdbc:sybase:Tds:{host}:{port}/{db}";
			}
			public String getDriver(){
				return "com.sybase.jdbc.SybDriver";
			}
			public String getDefaultPort(){
				return "5007";
			}
			
		},INFORMIX(){
			// default port : 1533
			public String getUrlModel(){
				return "jdbc:informix-sqli://{host}:{port}/myDB:INFORMIXSERVER={db}";
			}
			public String getDriver(){
				return "com.informix.jdbc.IfxDriver";
			}
			public String getDefaultPort(){
				return "1533";
			}
			
		},POSTGRESQL(){
			// default port : 5432
			public String getUrlModel(){
				return "jdbc:postgresql://{host}:{port}/{db}";
			}
			public String getDriver(){
				return "org.postgresql.Driver";
			}
			public String getDefaultPort(){
				return "5432";
			}
			
		};
		
		public String getUrlModel(){
			return null;
		}
		public String getDriver(){
			return null;
		}
		public String getDefaultPort(){
			return null;
		}
		
	}
