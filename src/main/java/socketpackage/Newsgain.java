package socketpackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gsy.MySQL_tools;
import gsy.News_Data;

public class Newsgain {
	String string = null;

	public Newsgain(String string) {
		// TODO Auto-generated constructor stub
		this.string = string;
	}

	public String getchannelnews() {
		Pattern pattern = Pattern.compile("<usremail>(.*?)</usremail><channel>(.*?)</channel>");
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		String channel = null;

		if (matcher.find()) {
			usremail = matcher.group(1);
			channel = matcher.group(2);

		}
		if (usremail == null) {
			return "获取失败";
		}
		MySQL_tools tools = new MySQL_tools();
		ArrayList<News_Data> news_Datas = tools.searchnews(channel, false);
		StringBuilder stringBuilder = new StringBuilder();
		for (News_Data news_Data : news_Datas) {
			stringBuilder.append("<item>");
			stringBuilder.append("<title>" + news_Data.getNews_title() + "</title>");
			stringBuilder.append("<link>" + news_Data.getNews_link() + "</link>");
			stringBuilder.append("<newsclass>" + news_Data.getNews_class() + "</newsclass>");
			stringBuilder.append("<author>" + news_Data.getNews_author() + "</author>");
			stringBuilder.append("<date>" + news_Data.getNews_pubdate() + "</date>");
			stringBuilder.append("<abstract>" + news_Data.getNews_abstract() + "</abstract>");
			stringBuilder.append("</item>");

		}
		tools.close();
		return stringBuilder.toString();
	}

	public String setreadnews() {
		Pattern pattern = Pattern.compile(
				"<usremail>(.*?)</usremail><newslink>(.*?)</newslink><newsclass>(.*?)</newsclass><flag>(.*?)</flag>");
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		String newslink = null;
		String newsclass = null;
		String flag = null;
		if (matcher.find()) {
			usremail = matcher.group(1);
			newslink = matcher.group(2);
			newsclass = matcher.group(3);
			flag = matcher.group(4);
			// usrtel = matcher.group(3);
		}
		if (usremail == null) {
			return "-1";
		}
		MySQL_tools sql_tools = new MySQL_tools();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(df.format(System.currentTimeMillis()));
		String da = df.format(System.currentTimeMillis());
		sql_tools.insertusrread(usremail, newsclass, newslink, da, Integer.valueOf(flag));
		sql_tools.close();
		return "1";
	}

}
