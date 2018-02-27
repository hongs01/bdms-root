package com.bdms.common.daemon;
//服务启动类
public class TestUtils {
	public static void main(String args[]) {
		System.out.println("execute main method!");
		}

		public void init() throws Exception {
		System.out.println("execute init method！");
		}

		public void init(String[] args) throws Exception{
		System.out.println("execute init(args) method");
		}

		public void start() throws Exception {
		System.out.println("execute start method！");
		}

		public void stop() throws Exception {
		System.out.println("execute stop method！");
		}

		public void destroy() throws Exception{
		System.out.println("execute destroy method!");
		}
}
