package clientdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatManager {

	// private ChatManager() {
	// }
	//
	// private static final ChatManager instance = new ChatManager();
	//
	// public static ChatManager getCM() {
	// return instance;
	// }
	String IP;
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;

	public void connect(String ip) {
		this.IP = ip;
		try {
			socket = new Socket(IP, 41129);
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			send("<function>getchannelnews</function><usremail>443554017@qq.com</usremail><channel>IT</channel>");
			send("<personaRandomAPPConnectstop>");
			// System.out.println("???");
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				// window.appendText("收到：1" + line);
				stringBuilder.append(line);
			}
			System.out.println(stringBuilder.toString());
			writer.close();
			reader.close();
			writer = null;
			reader = null;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String out) {
		if (writer != null) {
			writer.write(out + "\n");
			writer.flush();
		} else {
			// window.appendText("当前的链接已经中断");
			System.out.println("连接中断");
		}
	}

	public static void main(String[] args) {
		ChatManager chatManager = new ChatManager();
		chatManager.connect("z195872r87.51mypc.cn");

	}
}
