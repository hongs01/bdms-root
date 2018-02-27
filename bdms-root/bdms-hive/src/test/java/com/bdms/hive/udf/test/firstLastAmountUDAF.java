package com.bdms.hive.udf.test;
import java.util.ArrayList;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
import org.apache.hadoop.hive.ql.metadata.HiveException;  
import org.apache.hadoop.hive.ql.parse.SemanticException;  
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;  
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;  
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;  
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;  
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;  
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;  
import org.apache.hadoop.hive.serde2.objectinspector.StandardListObjectInspector;  
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;  
  
  
  
public class firstLastAmountUDAF extends AbstractGenericUDAFResolver {  
    @Override  
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] tis)  
            throws SemanticException  
    {  
        return new firstLastAmountUDAFEvaluator();  
    }  
  
      
    public static class firstLastAmountUDAFEvaluator extends GenericUDAFEvaluator {  
    	
        private PrimitiveObjectInspector inputOI;  
        private StandardListObjectInspector loi;  
        private StandardListObjectInspector internalMergeOI;  
          
        private String typeSameDay;  
  
        static class ArrayAggregationBuffer implements AggregationBuffer  
        {  
            ArrayList<Tuple> container;  
        }     
        @Override  
        public void reset(AggregationBuffer ab)  
                throws HiveException  
        {  
            ((ArrayAggregationBuffer) ab).container = new ArrayList<Tuple>();  
        }  
  
        @Override  
        public AggregationBuffer getNewAggregationBuffer()  
                throws HiveException  
        {  
            ArrayAggregationBuffer ret = new ArrayAggregationBuffer();  
            reset(ret);  
            return ret;  
        }  
  
        public static class Tuple {  
//      Caused by: java.lang.NoSuchMethodException: com.hive.prod.udaf.prod_topkUDAF$prod_topkUDAFEvaluator$Tuple.<init>()  
              public String minCreated;  
              public double minAmount;  
              public String maxCreated;  
              public double maxAmount;  
        }  
  
           public ObjectInspector init(Mode m, ObjectInspector[] parameters)  
                    throws HiveException  
            {  
                super.init(m, parameters);  
                if (m == Mode.PARTIAL1)  
                {  
                    inputOI = (PrimitiveObjectInspector) parameters[0];  
                    return ObjectInspectorFactory  
                            .getStandardListObjectInspector((PrimitiveObjectInspector) ObjectInspectorUtils  
                            .getStandardObjectInspector(inputOI));  
                }  
                else  
                {  
                    if (!(parameters[0] instanceof StandardListObjectInspector))  
                    {  
                        inputOI = (PrimitiveObjectInspector)  ObjectInspectorUtils  
                                .getStandardObjectInspector(parameters[0]);  
                        return (StandardListObjectInspector) ObjectInspectorFactory  
                                .getStandardListObjectInspector(inputOI);  
                    }  
                    else  
                    {  
                        internalMergeOI = (StandardListObjectInspector) parameters[0];  
                        inputOI = (PrimitiveObjectInspector) internalMergeOI.getListElementObjectInspector();  
                        loi = (StandardListObjectInspector) ObjectInspectorUtils.getStandardObjectInspector(internalMergeOI);  
                        return loi;  
                    }  
                }  
            }         
          
        public void iterate(AggregationBuffer ab, Object[] arg) {  
            //type='merge' 合并 ；type='single' 不用合并   
            String created=arg[0].toString();  
            double amount=Double.parseDouble(arg[1].toString());  
            String type=arg[2].toString();  
              
            typeSameDay=type;  
            String eL= "^\\d{4}-\\d{2}-\\d{2}$";     
            Pattern p = Pattern.compile(eL);      
            Matcher m = p.matcher(created);      
            boolean b = m.matches();      
            if(!b){  
                return ;  
            }  
            Tuple resultTuple = new Tuple();  
  
            ArrayAggregationBuffer agg = (ArrayAggregationBuffer) ab;  
            if (agg.container.size()==0){  
                resultTuple.minCreated=created;  
                resultTuple.minAmount=amount;  
                resultTuple.maxCreated=created;  
                resultTuple.maxAmount=amount;  
                agg.container.add((Tuple) ObjectInspectorUtils.copyToStandardObject(resultTuple, this.inputOI));  
                return;  
            }         
            if(agg.container.get(0).minCreated.compareTo(created)>0){  
                agg.container.get(0).minCreated=created;  
                agg.container.get(0).minAmount=amount;  
                return ;  
            }  
            if(agg.container.get(0).maxCreated.compareTo(created)<0){  
                agg.container.get(0).maxCreated=created;  
                agg.container.get(0).maxAmount=amount;  
                return ;  
            }  
            if(typeSameDay.equalsIgnoreCase("merge") ){  
                //同一天的进行合并  
                if(agg.container.get(0).maxCreated.compareTo(created)==0){  
                    agg.container.get(0).maxAmount+=amount;   
                }  
                if(agg.container.get(0).minCreated.compareTo(created)==0){  
                    agg.container.get(0).minAmount+=amount;  
                }  
            }  
            return ;  
        }  
  
        public Object terminatePartial(AggregationBuffer ab) {  
            ArrayAggregationBuffer agg = (ArrayAggregationBuffer) ab;  
            ArrayList<Tuple> ret = new ArrayList<Tuple>(agg.container.size());  
            Tuple partial = new Tuple();  
            partial.minCreated=agg.container.get(0).minCreated;  
            partial.minAmount=agg.container.get(0).minAmount;  
            partial.maxCreated=agg.container.get(0).maxCreated;  
            partial.maxAmount=agg.container.get(0).maxAmount;  
            ret.add((Tuple) ObjectInspectorUtils.copyToStandardObject(partial, this.inputOI));  
            agg.container.clear();  
            return ret;  
        }  
  
        public void merge(AggregationBuffer ab, Object o) {  
            ArrayAggregationBuffer agg = (ArrayAggregationBuffer) ab;  
            ArrayList<Tuple> otherList = (ArrayList<Tuple>)internalMergeOI.getList(o);  
            Tuple other = otherList.get(0);  
            if (otherList.size()==0){  
                return;  
            }  
            if (agg.container.size()==0){  
                agg.container.add((Tuple) ObjectInspectorUtils.copyToStandardObject(otherList.get(0), this.inputOI));  
                return;  
            }         
            if(agg.container.get(0).minCreated.compareTo(other.minCreated)>0){  
                agg.container.get(0).minCreated=other.minCreated;  
                agg.container.get(0).minAmount=other.minAmount;  
            }  
            if(agg.container.get(0).maxCreated.compareTo(other.maxCreated)<0){  
                agg.container.get(0).maxCreated=other.maxCreated;  
                agg.container.get(0).maxAmount=other.maxAmount;   
            }  
            if(typeSameDay.equalsIgnoreCase("merge") ){  
                //同一天不进行合并  
                if(agg.container.get(0).maxCreated.compareTo(other.maxCreated)==0){  
                    agg.container.get(0).maxAmount+=other.maxAmount;      
                }  
                if(agg.container.get(0).minCreated.compareTo(other.minCreated)==0){  
                    agg.container.get(0).minAmount+=other.minAmount;  
                }  
            }  
            return ;  
        }  
  
        public Object terminate(AggregationBuffer ab) {  
            ArrayAggregationBuffer agg = (ArrayAggregationBuffer) ab;  
            ArrayList<Tuple> ret = new ArrayList<Tuple>(agg.container.size());  
            Tuple partial = new Tuple();  
            partial.minCreated=agg.container.get(0).minCreated;  
            partial.minAmount=agg.container.get(0).minAmount;  
            partial.maxCreated=agg.container.get(0).maxCreated;  
            partial.maxAmount=agg.container.get(0).maxAmount;  
            ret.add((Tuple) ObjectInspectorUtils.copyToStandardObject(partial, this.inputOI));              
            agg.container.clear();  
            return ret;  
        }  
    }  
  
}  