package socketpackage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gsy.MySQL_tools;

public class Usrfunction {
	String string = null;

	public Usrfunction(String string) {
		// TODO Auto-generated constructor stub
		this.string = string;
	}

	// login
	public String usrlogin() {
		Pattern pattern = Pattern.compile("<usremail>(.*?)</usremail><usrpsw>(.*?)</usrpsw>");
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		String usrpsw = null;

		if (matcher.find()) {
			usremail = matcher.group(1);
			usrpsw = matcher.group(2);

		}
		if (usremail == null) {
			return "登录失败";
		}
		MySQL_tools sql_tools = new MySQL_tools();
		String psw = sql_tools.searchusrinfo(usremail, "usrpsw");
		String usrfav = sql_tools.searchusrinfo(usremail, "usrfav");
		sql_tools.close();
		if (usrpsw.equals(psw)) {
			return "登录成功" + "<usrfav>" + usrfav + "</usrfav>";
		} else {
			return "登录失败";
		}

	}

	public String usrregister() {
		Pattern pattern = Pattern.compile("<usremail>(.*?)</usremail><usrpsw>(.*?)</usrpsw><usrtel>(.*?)</usrtel>");
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		String usrpsw = null;
		String usrtel = null;
		if (matcher.find()) {
			usremail = matcher.group(1);
			usrpsw = matcher.group(2);
			usrtel = matcher.group(3);
		}
		if (usremail == null) {
			return "注册失败";
		}
		MySQL_tools sql_tools = new MySQL_tools();
		int f = sql_tools.insertuserinfo(usremail, usrpsw, usrtel, "");
		sql_tools.close();
		if (f == 1) {
			return "注册成功";
		} else if (f == -1) {
			return "已注册过";
		}

		return "";
	}

	public String usrchangepsw() {
		Pattern pattern = Pattern.compile("<usremail>(.*?)</usremail><usrpsw>(.*?)</usrpsw>");
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		String usrpsw = null;

		if (matcher.find()) {
			usremail = matcher.group(1);
			usrpsw = matcher.group(2);

		}
		if (usremail == null) {
			return "修改 失败";
		}
		MySQL_tools sql_tools = new MySQL_tools();
		int f = sql_tools.updateusrinfo(usremail, usrpsw);
		sql_tools.close();
		if (f == 1) {
			return "修改成功";
		} else if (f == -1) {
			return "修改失败";
		}
		return "";
	}

	public String setusrfav() {
		Pattern pattern = Pattern.compile("<usremail>(.*?)</usremail><usrfav>(.*?)</usrfav>");
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		String usrfav = null;
		if (matcher.find()) {
			usremail = matcher.group(1);
			usrfav = matcher.group(2);
			// usrtel = matcher.group(3);
		}
		if (usremail == null) {
			return "修改失败";
		}
		MySQL_tools sql_tools = new MySQL_tools();
		int f = sql_tools.updateusrinfofav(usremail, usrfav);
		sql_tools.close();
		if (f == 1) {
			return "修改成功";
		} else if (f == -1) {
			return "修改失败";
		}
		return "";

	}

}
