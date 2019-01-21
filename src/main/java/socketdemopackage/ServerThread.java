package socketdemopackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import recommend.Recommendmain;
import socketpackage.Newsgain;
import socketpackage.Sendidentfyingcode;
import socketpackage.Usrfunction;

public class ServerThread extends Thread {
	// 和本线程相关的Socket
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 线程执行的操作，响应客户端的请求
	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			// 获取输入流，并读取客户端信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((info = br.readLine()) != null) {// 循环读取客户端的信息
				System.out.println("我是服务器，客户端说：" + info);
				stringBuilder.append(info);
			}
			socket.shutdownInput();// 关闭输入流
			String string = stringBuilder.toString();
			Pattern pattern = Pattern.compile("<function>(.*?)</function>");
			Matcher matcher = pattern.matcher(string);
			String function = null;
			if (matcher.find()) {
				function = matcher.group(1);
			} else {
				function = "===";
			}
			System.out.println(function);
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
			// 获取输出流，响应客户端的请求
			os = socket.getOutputStream();
			// os.write((re).getBytes("UTF-8"));
			pw = new PrintWriter(os);
			pw.write(re);
			pw.flush();// 调用flush()方法将缓冲输出
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (pw != null)
					pw.close();
				if (os != null)
					os.close();
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (is != null)
					is.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}