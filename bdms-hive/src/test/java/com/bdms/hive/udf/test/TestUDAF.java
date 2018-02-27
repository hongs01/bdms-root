/**
 * @Copyright (C) 2013-2014 上海迪爱斯通信设备有限公司
 * @author 李晓聪
 * @date 2014-11-18 下午3:02:34
 */
package com.bdms.hive.udf.test;


import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFResolver2;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.util.StringUtils;
import org.apache.log4j.Logger;

/** 
 * @author 李晓聪
 * @date 2014-11-18 下午3:02:34
 * @Description:  TODO
 */

/**
 * An abstract class to help facilitate existing implementations of
 * <tt>GenericUDAFResolver</tt> to migrate towards the newly introduced
 * interface {@link GenericUDAFResolver2}. This class provides a default
 * implementation of this new API and in turn calls
 * the existing API {@link GenericUDAFResolver#getEvaluator(TypeInfo[])} by
 * ignoring the extra parameter information available via the
 * <tt>GenericUDAFParameterInfo</tt> interface.
 *
 */

//聚合函数   分析器
public class TestUDAF extends AbstractGenericUDAFResolver{
	
	private static final Logger log = Logger.getLogger(TestUDAF.class.getName());
	
	//重写  父类 AbstractGenericUDAFResolver中的  getEvaluator方法  返回 自己的    计算器
	public GenericUDAFEvaluator getEvaluator(TypeInfo[] info)
			throws SemanticException {
		return new GMEvaluator();
	}

	//聚合函数    计算器
	public static class GMEvaluator extends GenericUDAFEvaluator{
		
		private boolean warned = false;  
		
		private PrimitiveObjectInspector inputOI;  
        private DoubleWritable result;  
		
        //  初始化操作  及    确定 返回的结果的数据类型
		public ObjectInspector init(Mode m, ObjectInspector[] parameters)
				throws HiveException {
			 super.init(m, parameters);
	         inputOI = (PrimitiveObjectInspector) parameters[0];  
			 result = new DoubleWritable(0);  
			 return PrimitiveObjectInspectorFactory.writableDoubleObjectInspector;  
			
		}
		
		//创建新的聚合计算的需要的内存，用来存储mapper,combiner,reducer运算过程中的  相加总和。  
        //使用buffer对象前，先进行内存的清空——reset  
		public AggregationBuffer getNewAggregationBuffer() throws HiveException {
			
			SumAgg buffer = new SumAgg();  
            reset(buffer);  
            return buffer;  
		}
		
		//重置为0  
        //mapreduce支持mapper和reducer的重用，所以为了兼容，也需要做内存的重用。  
		public void reset(AggregationBuffer agg) throws HiveException {
			((SumAgg) agg).value = 0.0;  
            ((SumAgg) agg).empty = true; 
		}

		//迭代  
        //只要把保存当前和的对象  agg，再加上输入的参数，就可以了。  
		public void iterate(AggregationBuffer agg, Object[] parameters)
				throws HiveException {
			 
			// parameters == null means the input table/split is empty  
            if (parameters == null) {  
                return;  
            }  
            try {  
                   merge(agg, parameters[0]);   //这里将迭代数据放入combiner进行合并  
              } catch (NumberFormatException e) {  
                if (!warned) {  
                  warned = true;  
                  log.warn(getClass().getSimpleName() + " "  
                      + StringUtils.stringifyException(e));  
                }  
              }  
		}

		public void merge(AggregationBuffer agg, Object partial)
				throws HiveException {
			
			 if (partial != null) {
				 
				 SumAgg a =  ((SumAgg) agg);
	             // 通过ObejctInspector取每一个字段的数据  
                 double p = PrimitiveObjectInspectorUtils.getDouble(partial,inputOI);
                 a.value += p; 
                 a.empty = false;
	               
	            }  
			
		}

		public Object terminatePartial(AggregationBuffer agg)
				throws HiveException {
			return terminate(agg);  
		}
		
		public Object terminate(AggregationBuffer agg) throws HiveException {
			 	
			SumAgg myagg = (SumAgg) agg;  
	        result.set(myagg.value);  
	        return result;  
		}
		
		  //   存储结果的对象
        static class SumAgg implements AggregationBuffer {  
            boolean empty;  
            double value;  
        }  
		
	}

}
