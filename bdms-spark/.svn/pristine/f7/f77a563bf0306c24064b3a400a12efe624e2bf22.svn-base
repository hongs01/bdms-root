package com.bdms.spark.cassandra;
import java.nio.ByteBuffer;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
  
import org.apache.cassandra.thrift.Cassandra;  
import org.apache.cassandra.thrift.Column;  
import org.apache.cassandra.thrift.ColumnOrSuperColumn;  
import org.apache.cassandra.thrift.ColumnParent;  
import org.apache.cassandra.thrift.ColumnPath;  
import org.apache.cassandra.thrift.ConsistencyLevel;  
import org.apache.cassandra.thrift.KeyRange;  
import org.apache.cassandra.thrift.KeySlice;  
import org.apache.cassandra.thrift.SlicePredicate;  
import org.apache.cassandra.thrift.SliceRange;  
import org.apache.thrift.protocol.TBinaryProtocol;  
import org.apache.thrift.protocol.TProtocol;  
import org.apache.thrift.transport.TFramedTransport;  
import org.apache.thrift.transport.TSocket;  
import org.apache.thrift.transport.TTransport;  
/** 
 * java 操作 cassandra 增删改查 
 */  
public class TestCassandra {  
	
    private TTransport tr;  
    private Cassandra.Client client;  
  
    public TestCassandra(String ip, int port) {  
        this.tr = new TFramedTransport(new TSocket(ip, port));// 9160  
        TProtocol proto = new TBinaryProtocol(tr);  
        this.client = new Cassandra.Client(proto);  
    }  
  
    public void open() throws Exception {  
        tr.open();  
        if (!tr.isOpen()) {  
            throw new Exception("connect failed");  
        }  
    }  
  
    public void close() {  
        tr.close();  
    }  
  
    public void setKeySpace(String keyspace) throws Exception {  
        client.set_keyspace(keyspace);// 使用myKeyspace keyspace  
    }  
  
    /** 
     * 插入25条数据 
     * @throws Exception 
     */  
    public void insert(String columnFamily) throws Exception {  
    	
        ColumnParent parent = new ColumnParent(columnFamily);// column family  
  
        for (int i = 0; i < 25; i++) {  
        	
            long timestamp = System.currentTimeMillis();// 时间戳  
  
            Column nameColumn = new Column(toByteBuffer("name"));  
            nameColumn.setValue(toByteBuffer("name" + i));  
            nameColumn.setTimestamp(timestamp);  
            ByteBuffer nameColumnKey = toByteBuffer(i + "");  
            client.insert(nameColumnKey, parent, nameColumn,ConsistencyLevel.ONE);  
  
            Column ageColumn = new Column(toByteBuffer("age"));  
            ageColumn.setValue(toByteBuffer(i * 2 + ""));  
            ageColumn.setTimestamp(timestamp);  
            ByteBuffer ageColumnKey = toByteBuffer(i + "");  
            client.insert(ageColumnKey, parent, ageColumn, ConsistencyLevel.ONE);  
        }  
    }  
  
    /** 
     * 查询一个列的值 
     * @param key 
     * @param columnName 
     * @param columnFamily 
     * @throws Exception 
     */  
    public void findOneColumn(String key, String columnName, String columnFamily) throws Exception {  
        ColumnPath path = new ColumnPath(columnFamily);   
        path.setColumn(toByteBuffer(columnName)); // 读取id  
  
        ColumnOrSuperColumn column = client.get(toByteBuffer(key), path, ConsistencyLevel.ONE);  
        System.out.println(toString(column.column.name) + "->" + toString(column.column.value));  
    }  
  
    /** 
     * 查询一条数据所有列的值 
     * @param key 
     * @param columnFamily 
     * @throws Exception 
     */  
    public void findAllColumn(String key, String columnFamily) throws Exception {  
        ColumnParent parent = new ColumnParent(columnFamily);// column family  
  
        SlicePredicate predicate = new SlicePredicate();  
        SliceRange sliceRange = new SliceRange(toByteBuffer(""),  
                toByteBuffer(""), false, 10);  
        predicate.setSlice_range(sliceRange);  
        List<ColumnOrSuperColumn> results = client.get_slice(toByteBuffer(key),  
                parent, predicate, ConsistencyLevel.ONE);  
  
        for (ColumnOrSuperColumn result : results) {  
            System.out.print("{" + toString(result.column.name) + " -> "  
                    + toString(result.column.value) + "}  ");  
        }  
        System.out.println();  
    }  
  
    /** 
     * 查询多条数据 
     * @param columnFamily 
     * @throws Exception 
     */  
    public void findMulti(String columnFamily) throws Exception {  
        ColumnParent parent = new ColumnParent(columnFamily);// column family  
  
        SlicePredicate predicate = new SlicePredicate();  
        SliceRange sliceRange = new SliceRange();  
        sliceRange.setStart("".getBytes());  
        sliceRange.setFinish("".getBytes());  
          
        predicate.setSlice_range(sliceRange);  
  
        List<ByteBuffer> keys = new ArrayList<>();  
        keys.add(toByteBuffer("1"));  
        keys.add(toByteBuffer("2"));  
        keys.add(toByteBuffer("3"));  
        keys.add(toByteBuffer("4"));  
        keys.add(toByteBuffer("5"));  
  
        Map<ByteBuffer, List<ColumnOrSuperColumn>> multiMap = client.multiget_slice(keys, parent, predicate, ConsistencyLevel.ONE);  
  
        for (Entry<ByteBuffer, List<ColumnOrSuperColumn>> entry : multiMap .entrySet()) {  
            System.out.print("key=" + toString(entry.getKey()) + "  ");  
            List<ColumnOrSuperColumn> value = entry.getValue();  
            for (ColumnOrSuperColumn column : value) {  
                System.out.print("{" + toString(column.column.name) + " -> " + toString(column.column.value) + "}  ");  
            }  
            System.out.println();  
        }  
    }  
  
    /** 
     * 使用KeyRange查询 
     * @param columnFamily 
     * @throws Exception 
     */  
    public void findRange(String columnFamily)throws Exception{  
        ColumnParent parent = new ColumnParent(columnFamily);// column family  
        KeyRange keyRange = new KeyRange();  
        keyRange.setStart_key("".getBytes());    
        keyRange.setEnd_key("".getBytes());     
          
        SlicePredicate predicate = new SlicePredicate();    
        List<ByteBuffer> column_names = new ArrayList<>();    
        column_names.add(toByteBuffer("age"));    
        column_names.add(toByteBuffer("name"));  
        predicate.setColumn_names(column_names);  
  
        List<KeySlice> get_range_slices = client.get_range_slices(parent, predicate, keyRange, ConsistencyLevel.ONE);  
        for(KeySlice keySlice : get_range_slices){  
            System.out.print("key=" + toString(keySlice.key) + "  ");  
            for (ColumnOrSuperColumn column : keySlice.columns) {  
                System.out.print("{" + toString(column.column.name) + " -> " + toString(column.column.value) + "}  ");  
            }  
            System.out.println();  
        }  
    }  
  
      
    /** 
     * 删除 
     * @param key 
     * @param columnFamily 
     * @throws Exception 
     */  
    public void remove(String key,String columnFamily)throws Exception{  
        ColumnPath path = new ColumnPath(columnFamily);   
        long temp = System.currentTimeMillis();  
        client.remove(toByteBuffer(key), path, temp, ConsistencyLevel.ONE);  
    }  
      
    /** 
     * 更新 
     * @param key 
     * @param columnName 
     * @param columnFamily 
     * @param value 
     * @throws Exception 
     */  
    public void update(String key,String columnName,String columnFamily,String value)throws Exception{  
        ColumnParent parent = new ColumnParent(columnFamily);// column family  
          
        long timestamp = System.currentTimeMillis();// 时间戳  
        Column ageColumn = new Column(toByteBuffer(columnName));  
        ageColumn.setValue(toByteBuffer(value));  
        ageColumn.setTimestamp(timestamp);  
        ByteBuffer ageColumnKey = toByteBuffer(key);  
        client.insert(ageColumnKey, parent, ageColumn, ConsistencyLevel.ONE);  
    }  
      
    public static void main(String[] args) throws Exception {  
    	
        TestCassandra tc = new TestCassandra("127.0.0.1", 9160);  
        tc.open();  
        tc.setKeySpace("usermanager");  
  
//      tc.insert("users");  
  
    // tc.findOneColumn("2","name","users");  
    // tc.findOneColumn("2","age","users");  
  
        tc.findAllColumn("2","users");  
  
//      tc.findMulti("users");  
//      tc.remove("usermanager", "users");  
          
//      tc.findRange("users");  
          
        tc.findOneColumn("2","age","users");  
          
        tc.update("2", "age","users","111");  
          
        tc.findOneColumn("2","age","users");  
          
        tc.close();  
    }  
  
      
      
    public static ByteBuffer toByteBuffer(String value) throws Exception {  
        return ByteBuffer.wrap(value.getBytes("UTF-8"));  
    }  
  
    public static String toString(ByteBuffer buffer) throws Exception {  
        byte[] bytes = new byte[buffer.remaining()];  
        buffer.get(bytes);  
        return new String(bytes, "UTF-8");  
    }  
} 