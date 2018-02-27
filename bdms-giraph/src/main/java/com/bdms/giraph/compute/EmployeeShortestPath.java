package com.bdms.giraph.compute;

import java.io.IOException;

import org.apache.giraph.Algorithm;
import org.apache.giraph.conf.IntConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/* 
 * Description:
 * 	雇员最短路径算法实现
 * 
 * History：
 * ========================================
 * Date         Version  Memo
 * 2014-12-05   0.1      Created by YuXiaolin
 * 
 * ========================================
 * 
 * Copyright 2014, 迪爱斯通信设备有限公司保留。
 */

@Algorithm(
/* 定义运算法则 */
name = "emp_shortest_path", description = "Finds all shortest paths from a selected vertex")
public class EmployeeShortestPath extends
		BasicComputation<Text, IntWritable, NullWritable, IntWritable> {

	private IntWritable max = new IntWritable(Integer.MAX_VALUE);

	private IntWritable msg = new IntWritable(1);

	/* 最短路径的id */
	public static final IntConfOption SOURCE_ID = new IntConfOption("雇员的id", 0,
			"雇员到达目标顶点最短路径.");

	/**
	 * 判断是否为顶点资源
	 * 
	 * @param vertex
	 * @return
	 */
	private boolean isSource(Vertex<Text, IntWritable, NullWritable> vertex) {
		return vertex.getId().toString() == SOURCE_ID.getKey();
	}

	/*
	 * (non-Javadoc) 实验最短路径计算方法
	 * 
	 * @see
	 * org.apache.giraph.graph.AbstractComputation#compute(org.apache.giraph
	 * .graph.Vertex, java.lang.Iterable)
	 */
	@Override
	public void compute(Vertex<Text, IntWritable, NullWritable> vertex,
			Iterable<IntWritable> messages) throws IOException {
		/* 获取超步状态 */
		if (getSuperstep() == 0) {
			vertex.setValue(max); // 设置顶点值为大
			if (isSource(vertex)) {
				for (Edge<Text, NullWritable> e : vertex.getEdges()) {
					sendMessage(e.getTargetVertexId(), msg); // 跟目标顶点发送消息
				}
			}
			int min = vertex.getValue().get(); // 获取顶点值
			
			for(IntWritable msg : messages) {
                min = Math.min(msg.get(), min);
            }
            if(min < vertex.getValue().get()) {
            	vertex.setValue(new IntWritable(min));
                msg.set(min + 1);
                sendMessageToAllEdges(vertex, msg);
            }
            vertex.voteToHalt();
		}

	}

}
