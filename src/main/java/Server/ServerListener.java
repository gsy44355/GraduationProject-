package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {

	@Override
	public void run() {
		// 1-65535
		System.out.println("服务已经启动");
		try {
			ServerSocket serverSocket = new ServerSocket(41129);
			while (true) {
				// block
				Socket socket = serverSocket.accept();
				// 建立连接
				// JOptionPane.showMessageDialog(null, "有客户端链接到了本机的41129端口");
				System.out.println("有客户端链接到了本机的41129端口");
				// 将socket传递给新的线程
				ChatSocket cs = new ChatSocket(socket);
				cs.start();
				ChatManager.getChatManager().add(cs);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
