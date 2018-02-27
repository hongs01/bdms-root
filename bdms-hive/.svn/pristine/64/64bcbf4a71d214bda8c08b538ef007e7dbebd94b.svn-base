package com.bdms.hive.udf.test;
import java.util.ArrayList;
    
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;


/**
 * @author 李晓聪
 * @date 2014-11-20 上午9:09:57
 * @Description:  TODO
 * 
 *  继承org.apache.hadoop.hive.ql.udf.generic.GenericUDTF。
 	实现initialize, process, close三个方法
 	UDTF首先会调用initialize方法，此方法返回UDTF的返回行的信息（返回个数，类型）。初始化完成后，会调用process方法，对传入的参数进行处理，可以通过forword()方法把结果返回。最后close()方法调用，对需要清理的方法进行清理。
 
 	1：直接select中使用：select explode_map(properties) as (col1,col2) from src;
	2：和lateral view一起使用：select src.id, mytable.col1, mytable.col2 from src lateral view explode_map(properties) mytable as col1, col2;
 
	      不可以添加其他字段使用：select a, explode_map(properties) as (col1,col2) from src
	      不可以嵌套调用：select explode_map(explode_map(properties)) from src
	      不可以和group by/cluster by/distribute by/sort by一起使用：select explode_map(properties) as (col1,col2) from src group by col1, col2

	实现对  key:value;key:value的切分
 */
public class TestUDTF extends GenericUDTF{
   
        @Override
        public void close() throws HiveException {
            // TODO Auto-generated method stub    
        }
   
        //验证输入的参数个数     并设置 输出的参数的信息 包括 输出的参数的个数 和类型
        public StructObjectInspector initialize(ObjectInspector[] args)
                throws UDFArgumentException {
            if (args.length != 1) {
                throw new UDFArgumentLengthException("ExplodeMap takes only one argument");
            }
            if (args[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
                throw new UDFArgumentException("ExplodeMap takes string as a parameter");
            }
   
            ArrayList<String> fieldNames = new ArrayList<String>();
            ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
            fieldNames.add("col1");
            fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
            fieldNames.add("col2");
            fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
   
            return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs);
        }
   
       //对 输入的参数  进行切分
        public void process(Object[] args) throws HiveException {
            String input = args[0].toString();
            String[] test = input.split(";");
            for(int i=0; i<test.length; i++) {
                try {
                    String[] result = test[i].split(":");
                    forward(result);
                } catch (Exception e) {
                   continue;
               }
          }
        }
    }
 