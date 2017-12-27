package nio;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MainServe {
	public static void main(String[] args) throws Exception {
		//����һ����������Server��Socket,��NIO
		SocketAcceptor acceptor = new NioSocketAcceptor();
		//�����������ݵĹ�����
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		//�趨�����������һ��һ��(/r/n)�Ķ�ȡ����
		chain.addLast("myChin", new ProtocolCodecFilter(new TextLineCodecFactory()));
		//�趨�������˵���Ϣ������:һ��SamplMinaServerHandler����,
		acceptor.setHandler(new SamplMinaServerHandler());
		// �������˰󶨵Ķ˿�
		int bindPort=9090;
		//�󶨶˿�,����������
		acceptor.bind(new InetSocketAddress(bindPort));
		System.out.println("Mina Server is Listing on:= " + bindPort);
		}

}
