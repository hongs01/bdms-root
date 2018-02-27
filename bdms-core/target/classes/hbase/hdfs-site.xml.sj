<!--Fri Aug 28 15:12:14 2015-->
    <configuration>
    
    <property>
      <name>dfs.block.access.token.enable</name>
      <value>true</value>
    </property>
    
    <property>
      <name>dfs.blockreport.initialDelay</name>
      <value>120</value>
    </property>
    
    <property>
      <name>dfs.blocksize</name>
      <value>134217728</value>
    </property>
    
    <property>
      <name>dfs.client.read.shortcircuit</name>
      <value>true</value>
    </property>
    
    <property>
      <name>dfs.client.read.shortcircuit.streams.cache.size</name>
      <value>4096</value>
    </property>
    
    <property>
      <name>dfs.cluster.administrators</name>
      <value>hdfs</value>
    </property>
    
    <property>
      <name>dfs.datanode.address</name>
      <value>0.0.0.0:50010</value>
    </property>
    
    <property>
      <name>dfs.datanode.balance.bandwidthPerSec</name>
      <value>6250000</value>
    </property>
    
    <property>
      <name>dfs.datanode.data.dir</name>
      <value>/hadoop/hdfs/data</value>
    </property>
    
    <property>
      <name>dfs.datanode.data.dir.perm</name>
      <value>750</value>
    </property>
    
    <property>
      <name>dfs.datanode.du.reserved</name>
      <value>1073741824</value>
    </property>
    
    <property>
      <name>dfs.datanode.failed.volumes.tolerated</name>
      <value>0</value>
    </property>
    
    <property>
      <name>dfs.datanode.http.address</name>
      <value>0.0.0.0:50075</value>
    </property>
    
    <property>
      <name>dfs.datanode.https.address</name>
      <value>0.0.0.0:50475</value>
    </property>
    
    <property>
      <name>dfs.datanode.ipc.address</name>
      <value>0.0.0.0:8010</value>
    </property>
    
    <property>
      <name>dfs.datanode.max.transfer.threads</name>
      <value>16384</value>
    </property>
    
    <property>
      <name>dfs.domain.socket.path</name>
      <value>/var/lib/hadoop-hdfs/dn_socket</value>
    </property>
    
    <property>
      <name>dfs.heartbeat.interval</name>
      <value>3</value>
    </property>
    
    <property>
      <name>dfs.hosts.exclude</name>
      <value>/etc/hadoop/conf/dfs.exclude</value>
    </property>
    
    <property>
      <name>dfs.http.policy</name>
      <value>HTTP_ONLY</value>
    </property>
    
    <property>
      <name>dfs.https.port</name>
      <value>50470</value>
    </property>
    
    <property>
      <name>dfs.journalnode.edits.dir</name>
      <value>/hadoop/hdfs/journalnode</value>
    </property>
    
    <property>
      <name>dfs.journalnode.http-address</name>
      <value>0.0.0.0:8480</value>
    </property>
    
    <property>
      <name>dfs.journalnode.https-address</name>
      <value>0.0.0.0:8481</value>
    </property>
    
    <property>
      <name>dfs.namenode.accesstime.precision</name>
      <value>0</value>
    </property>
    
    <property>
      <name>dfs.namenode.avoid.read.stale.datanode</name>
      <value>true</value>
    </property>
    
    <property>
      <name>dfs.namenode.avoid.write.stale.datanode</name>
      <value>true</value>
    </property>
    
    <property>
      <name>dfs.namenode.checkpoint.dir</name>
      <value>/hadoop/hdfs/namesecondary</value>
    </property>
    
    <property>
      <name>dfs.namenode.checkpoint.edits.dir</name>
      <value>${dfs.namenode.checkpoint.dir}</value>
    </property>
    
    <property>
      <name>dfs.namenode.checkpoint.period</name>
      <value>21600</value>
    </property>
    
    <property>
      <name>dfs.namenode.checkpoint.txns</name>
      <value>1000000</value>
    </property>
    
    <property>
      <name>dfs.namenode.handler.count</name>
      <value>100</value>
    </property>
    
    <property>
      <name>dfs.namenode.http-address</name>
      <value>hadoop-1:50070</value>
      <final>true</final>
    </property>
    
    <property>
      <name>dfs.namenode.https-address</name>
      <value>hadoop-1:50470</value>
    </property>
    
    <property>
      <name>dfs.namenode.name.dir</name>
      <value>/hadoop/hdfs/namenode</value>
    </property>
    
    <property>
      <name>dfs.namenode.name.dir.restore</name>
      <value>true</value>
    </property>
    
    <property>
      <name>dfs.namenode.safemode.threshold-pct</name>
      <value>1.0f</value>
    </property>
    
    <property>
      <name>dfs.namenode.secondary.http-address</name>
      <value>hadoop-2:50090</value>
    </property>
    
    <property>
      <name>dfs.namenode.stale.datanode.interval</name>
      <value>30000</value>
    </property>
    
    <property>
      <name>dfs.namenode.startup.delay.block.deletion.sec</name>
      <value>3600</value>
    </property>
    
    <property>
      <name>dfs.namenode.write.stale.datanode.ratio</name>
      <value>1.0f</value>
    </property>
    
    <property>
      <name>dfs.permissions.enabled</name>
      <value>true</value>
    </property>
    
    <property>
      <name>dfs.permissions.superusergroup</name>
      <value>hdfs</value>
    </property>
    
    <property>
      <name>dfs.replication</name>
      <value>3</value>
    </property>
    
    <property>
      <name>dfs.replication.max</name>
      <value>50</value>
    </property>
    
    <property>
      <name>dfs.support.append</name>
      <value>true</value>
      <final>true</final>
    </property>
    
    <property>
      <name>dfs.webhdfs.enabled</name>
      <value>true</value>
    </property>
    
    <property>
      <name>fs.permissions.umask-mode</name>
      <value>022</value>
    </property>
    
  </configuration>
