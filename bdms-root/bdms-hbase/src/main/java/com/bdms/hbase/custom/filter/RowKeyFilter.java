package com.bdms.hbase.custom.filter;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
 
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.filter.FilterBase;
import org.apache.hadoop.hbase.util.Bytes;
 
/**
 * @description 自定义过滤器，用来读取大量离散行
 * @author Administrator
 * @time 2014年11月8日上午10:47:17
 * @className RowKeyFilter
 */
public class RowKeyFilter extends FilterBase
{
    private byte[] value = null;
    private boolean filterRow = true;
    /**
     * map中存放需要读的行RowKey
     */
    public Map<Object, Object> map = new HashMap<Object, Object>();
 
    public RowKeyFilter()
    {
        super();
    }
 
    public RowKeyFilter(byte[] value)
    {
        this.value = value;
    }
 
    public ReturnCode filterKeyValue(KeyValue ignored)
    {
        if (this.filterRow == false)
            return ReturnCode.INCLUDE;
        else
            return ReturnCode.NEXT_ROW;
    }
 
    /**
     * 行过滤，查询该行RowKey是否在Map中
     */
    @Override
    public boolean filterRowKey(byte[] buffer, int offset, int length)
    {
        byte[] rowKey = Arrays.copyOfRange(buffer, offset, offset + length);
        String str = new String(rowKey);
        if (map.containsKey(str))
        { // 在0(1)时间内返回，效率较高
            this.filterRow = false; // false表示包括这一行
        }
        return this.filterRow;
    }
 
    @Override
    public void reset()
    {
        this.filterRow = true;
    }
 
    @Override
    public boolean filterRow()
    {
        return filterRow;
    }
 
    /**
     * 将Map中的数据以Byte[]形式传给服务器
     */
    public void write(DataOutput dataOutput) throws IOException
    {
        Bytes.writeByteArray(dataOutput, this.value);
    }
 
    /**
     * 服务器读取Byte[]数据，再将数据存储到Map中 不同的RowKey以","分割
     */
    public void readFields(DataInput dataInput) throws IOException
    {
        this.value = Bytes.readByteArray(dataInput);
 
        String string = new String(this.value);
        String[] strs = string.split(",");
        for (String str : strs)
        {
            map.put(str, str);
        }
    }
}