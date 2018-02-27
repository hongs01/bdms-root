<!--Fri Aug 28 15:10:24 2015-->
    <configuration>
    
    <property>
      <name>fs.defaultFS</name>
      <value>hdfs://hadoop-1:8020</value>
      <final>true</final>
    </property>
    
    <property>
      <name>fs.trash.interval</name>
      <value>360</value>
    </property>
    
    <property>
      <name>ha.failover-controller.active-standby-elector.zk.op.retries</name>
      <value>120</value>
    </property>
    
    <property>
      <name>hadoop.http.authentication.simple.anonymous.allowed</name>
      <value>true</value>
    </property>
    
    <property>
      <name>hadoop.proxyuser.hcat.groups</name>
      <value>users</value>
    </property>
    
    <property>
      <name>hadoop.proxyuser.hcat.hosts</name>
      <value>hadoop-2</value>
    </property>
    
    <property>
      <name>hadoop.proxyuser.hive.groups</name>
      <value>users</value>
    </property>
    
    <property>
      <name>hadoop.proxyuser.hive.hosts</name>
      <value>hadoop-2</value>
    </property>
    
    <property>
      <name>hadoop.proxyuser.oozie.groups</name>
      <value>*</value>
    </property>
    
    <property>
      <name>hadoop.proxyuser.oozie.hosts</name>
      <value>hadoop-2</value>
    </property>
    
    <property>
      <name>hadoop.security.auth_to_local</name>
      <value>DEFAULT</value>
    </property>
    
    <property>
      <name>hadoop.security.authentication</name>
      <value>simple</value>
    </property>
    
    <property>
      <name>hadoop.security.authorization</name>
      <value>false</value>
    </property>
    
    <property>
      <name>io.compression.codecs</name>
      <value>org.apache.hadoop.io.compress.GzipCodec,org.apache.hadoop.io.compress.DefaultCodec,org.apache.hadoop.io.compress.SnappyCodec</value>
    </property>
    
    <property>
      <name>io.file.buffer.size</name>
      <value>131072</value>
    </property>
    
    <property>
      <name>io.serializations</name>
      <value>org.apache.hadoop.io.serializer.WritableSerialization</value>
    </property>
    
    <property>
      <name>ipc.client.connect.max.retries</name>
      <value>50</value>
    </property>
    
    <property>
      <name>ipc.client.connection.maxidletime</name>
      <value>30000</value>
    </property>
    
    <property>
      <name>ipc.client.idlethreshold</name>
      <value>8000</value>
    </property>
    
    <property>
      <name>ipc.server.tcpnodelay</name>
      <value>true</value>
    </property>
    
    <property>
      <name>mapreduce.jobtracker.webinterface.trusted</name>
      <value>false</value>
    </property>
    
    <property>
      <name>proxyuser_group</name>
      <value>users</value>
    </property>
    
  </configuration>
