package nio;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class SamplMinaServerHandler extends IoHandlerAdapter {
	// ��һ���Ͷ˶��������ʱ
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("incomming client : " + session.getRemoteAddress());
	}

	// ��һ���ͻ��˹ر�ʱ
	@Override
	public void sessionClosed(IoSession session) {
		System.out.println("one Clinet Disconnect !");
	}

	// ���ͻ��˷��͵���Ϣ����ʱ:
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// ���Ǽ��趨�˷�����������Ϣ�Ĺ�����һ��һ�ж�ȡ,����Ϳ�תΪString:
		String s = (String) message;
		// Write the received data back to remote peer
		System.out.println("�յ��ͻ�����������Ϣ: " + s);
		// ���Խ���Ϣ���͸��ͻ���
		session.write(s + count);
		count++;
	}

	private int count = 0;
}
