package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import recommend.Recommendmain;
import socketpackage.Newsgain;
import socketpackage.Sendidentfyingcode;
import socketpackage.Usrfunction;

public class ChatSocket extends Thread {

	Socket socket;

	public ChatSocket(Socket s) {
		this.socket = s;
	}

	public void out(String out) {
		try {
			socket.getOutputStream().write((out + "\n").getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("断开了一个客户端链接");
			ChatManager.getChatManager().remove(this);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// out("你已经连接到本服务器了");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
				if (line.contains("<personaRandomAPPConnectstop>")) {
					break;
				}
				// System.out.println(line);
				// ChatManager.getChatManager().publish(this, line);
			}
			String string = stringBuilder.toString();
			System.out.println(string);
			Pattern pattern = Pattern.compile("<function>(.*?)</function>");
			Matcher matcher = pattern.matcher(string);
			String function = null;
			if (matcher.find()) {
				function = matcher.group(1);
			} else {
				function = "===";
			}
			// System.out.println(function);
			String re = null;
			switch (function) {
			case "register":
				re = new Usrfunction(string).usrregister();
				break;
			case "login":
				re = new Usrfunction(string).usrlogin();
				break;
			case "changepsw":
				re = new Usrfunction(string).usrchangepsw();
				break;
			case "getrecommend":
				re = new Recommendmain(string).derecommend();
				break;
			case "getchannelnews":
				re = new Newsgain(string).getchannelnews();
				break;
			case "putreadnews":
				re = new Newsgain(string).setreadnews();
				break;
			case "putfav":
				re = new Usrfunction(string).setusrfav();
				break;
			case "getidentfyingcode":
				re = new Sendidentfyingcode(string).sendemail();
				break;
			default:
				re = "错误";
				break;
			}
			out(re);
			br.close();
			System.out.println("断开了一个客户端链接");
			ChatManager.getChatManager().remove(this);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("断开了一个客户端链接");
			ChatManager.getChatManager().remove(this);
			e.printStackTrace();
		}

	}
}
