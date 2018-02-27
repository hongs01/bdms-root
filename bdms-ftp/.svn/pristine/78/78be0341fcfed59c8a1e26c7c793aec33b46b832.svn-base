package com.bdms.ftp.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MinaClientHandler implements IoHandler {

	// 当客户端连接进入时  
    @Override  
    public void sessionOpened(IoSession session) throws Exception {  
        //session.write("i am coming");  
    }  
  
    @Override  
    public void exceptionCaught(IoSession session, Throwable cause)  
            throws Exception {  
        System.out.println("客户端发送信息异常....");  
    }  
  
    // 当客户端发送消息到达时  
    @Override  
    public void messageReceived(IoSession session, Object message)  
            throws Exception {  
    
        System.out.println("服务器返回的数据：" + message.toString()); 
       // session.write(message);
    }  
  
    @Override  
    public void sessionClosed(IoSession session) throws Exception {  
        System.out.println("客户端与服务端断开连接....." + session.getId());  
        
    }  
  
    @Override  
    public void sessionCreated(IoSession session) throws Exception {  
        // TODO Auto-generated method stub  
        //System.out  
        //        .println("one Client Connection" + session.getRemoteAddress());  
        //session.write("我来了······");  
    }

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		
	}  
  
	

}
