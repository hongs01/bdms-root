package com.bdms.giraph.io;

import java.io.IOException;

import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/* 
 * Description:
 * 		雇员输出格式
 * 
 * History：
 * ========================================
 * Date         Version  Memo
 * 2014-12-04   0.1      Created by YuXiaolin
 * ========================================
 * 
 * Copyright 2014, 迪爱斯通信设备有限公司保留。
 */
public class EmployeeShortestPathOutputFormat extends
		TextVertexOutputFormat<Text, IntWritable, NullWritable> {

	public class EmployeeRDFVertexWriter extends TextVertexWriter {

		private Text valOut = new Text();

		public void writeVertex(Vertex<Text, IntWritable, NullWritable> vertex)
				throws IOException, InterruptedException {
			valOut.set(vertex.getValue().toString());
			if (vertex.getValue().get() == Integer.MAX_VALUE) {
				valOut.set("no path");
			}
		}
	}

	@Override
	public TextVertexWriter createVertexWriter(TaskAttemptContext context)
			throws IOException, InterruptedException {
		return new EmployeeRDFVertexWriter();
	}

}
