package com.bdms.giraph.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexInputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;


/* 
 * Description:
 * 		雇员RDF文件格式类，用来读取文件，组合成图的RDF三元组。
 * 
 * History：
 * ========================================
 * Date         Version  Memo
 * 2014-12-04   0.1      Created by YuXiaolin
 * 
 * 
 * ========================================
 * 
 * Copyright 2015, 迪爱斯通信设备有限公司保留。
 */
public class EmployeeRDFTextInputFormat extends
TextVertexInputFormat<Text, IntWritable, NullWritable> {

public TextVertexReader createVertexReader(InputSplit split,
	TaskAttemptContext context) throws IOException {
return new EmployeeRDFVertexReader();
}

public class EmployeeRDFVertexReader
	extends
	TextVertexInputFormat<Text, IntWritable, NullWritable>.TextVertexReader {
private final Pattern TAB = Pattern.compile("[\\t]");
private final Pattern COLON = Pattern.compile("[:]");
private final Pattern COMMA = Pattern.compile("[,]");



@Override
public boolean nextVertex() throws IOException, InterruptedException {
	return getRecordReader().nextKeyValue();
}

/*
 * 数据格式: Loria Bargas role:chief architect subordinates:Eugena
 * Bohnert,Les Sasse,Polly Nicola,Latisha Mckenney
 */

@Override
public Vertex<Text, IntWritable, NullWritable> getCurrentVertex()
		throws IOException, InterruptedException {

	Vertex<Text, IntWritable, NullWritable> vertex = getConf()
			.createVertex(); // 根据配置conf配置的顶点，实例化一个顶点

	String[] tokens = TAB.split(getRecordReader().getCurrentValue()
			.toString()); // 截取记录的字符串

	Text vertexId = new Text(tokens[0]); // 雇员的姓名作为顶点的Id
	IntWritable value = new IntWritable(0);// 0作为顶点的值
	String subtoken = COLON.split(tokens[2])[1];// 附属关系
	String[] subs = COMMA.split(subtoken);// 所有附属关系数组

	List<Edge<Text, NullWritable>> edges = new ArrayList<Edge<Text, NullWritable>>();

	for (final String sub : subs) {
		if (sub.equals("none")) {
			Edge<Text, NullWritable> edge = new Edge<Text, NullWritable>() {
				public NullWritable getValue() {
					return NullWritable.get();
				}

				public Text getTargetVertexId() {
					return new Text(sub);
				}
			};
			edges.add(edge);
		}
	}
	vertex.initialize(vertexId, value, edges);
	/*
      * 顶点格式化之后的数据
      * [姓名，0，[[目标姓名，null],[目标姓名,null],...]]
      * */
	return vertex;
}

}

}
