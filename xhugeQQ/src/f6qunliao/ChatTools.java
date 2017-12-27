package f6qunliao;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器管理客户处理线程,转发消息的类 这个类只需要提供方法调用,不需要创建对象,所以皆为静态方法
 * 
 * @author 蓝杰 胡东峰
 */
public class ChatTools {
	// 保存处理线程的队列对象
	private static List<ServerThread> stList = new ArrayList<ServerThread>();

	// 不需要在外部创建此类的对象,所以构造器访问限定符写为private
	private ChatTools() {
	}

	/**
	 * 将一个客户对应的处理线程对象加入到队列中
	 * 
	 * @param ct
	 *            :处理线程对象
	 */
	public static void addClient(ServerThread ct) {
		// 通知己在线用户，有人上线了:
		castMsg(ct.getOwerUser(), "我上线啦！目前人数" + stList.size());
		stList.add(ct);// 将这个处理线程对象加入到队列中
	}

	/**
	 * 将一条消息发送给队列中的其他客户机处理对象
	 * 
	 * @param sender
	 *            :发送者用户对象
	 * @param msg
	 *            :要发送的消息内容
	 */
	public static void castMsg(UserInfo sender, String msg) {
		msg = sender.getName() + "说:" + msg;
		for (int i = 0; i < stList.size(); i++) {
			ServerThread st = stList.get(i);
			// ServerThread类中定义有一个将消息发送出去的方法
			st.sendMsg2Me(msg);// 发消息给每一个客户机
		}
	}
}
