package gsy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import record.News_URL;

public class Catalog_get {
	private final String REGEX_ITEM = "(?s)<item>(.*?)</item>";// 新闻项
	private final String REGEX_TITLE = "(?s)<title><!\\[CDATA\\[(.*?)\\]\\]></title>";// 标题
	private final String REGEX_LINK = "(?s)<link>(.*?)</link>";// 链接
	private final String REGEX_DATE = "(?s)<pubDate>(.*?)</pubDate>";// 发布日期
	private final String REGEX_AUTHOR = "(?s)<author>(.*?)</author>";// 作者
	private final String REGEX_ABSTRACT = "(?s)<description>(.*?)</description>";// 正文

	// private final String REGEX_CONTENT =
	// "(?s)<title><!\\[CDATA\\[(.*?)\\]\\]></title><link>(.+?)</link><pubDate>(.*?)</pubDate><author>(.*?)</author><description>(.*?)</description>";
	// String searchURL = "http://www.people.com.cn/rss/politics.xml";
	public ArrayList<News_Data> Catalog_get(String searchURL) {
		// TODO Auto-generated constructor stub

		String string = "";
		URL url = null;
		try {
			url = new URL(searchURL);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s = br.readLine()) != null) {

				sb.append(s);
			}
			string = sb.toString();
			// url = new URL(searchURL);
			// HttpURLConnection conn = (HttpURLConnection)
			// url.openConnection();
			// conn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR
			// 3.0.04506)");
			// InputStream content = conn.getInputStream();
			// StringBuilder out = new StringBuilder();
			// byte[] b = new byte[4096];
			// for (int n; (n = content.read(b)) != -1;) {
			// out.append(new String(b, 0, n, "UTF-8"));
			// }
			// string = out.toString();
			// System.out.println(string);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(string);
		ArrayList<News_Data> news_Datas = new ArrayList<News_Data>();
		Pattern pattern = Pattern.compile(REGEX_ITEM);
		Matcher matcheritem = pattern.matcher(string);
		while (matcheritem.find()) {
			News_Data news_Data = new News_Data();
			String string2 = matcheritem.group(1);
			Pattern pattern2 = Pattern.compile(REGEX_TITLE);
			Matcher matcher = pattern2.matcher(string2);
			if (matcher.find()) {
				// System.out.println(matcher.group(1));
				news_Data.setNews_title(matcher.group(1));
			}
			pattern2 = Pattern.compile(REGEX_LINK);
			matcher = pattern2.matcher(string2);
			if (matcher.find()) {
				// System.out.println(matcher.group(1));
				news_Data.setNews_link(matcher.group(1));
			}
			pattern2 = Pattern.compile(REGEX_DATE);
			matcher = pattern2.matcher(string2);
			if (matcher.find()) {
				// System.out.println(matcher.group(1));
				news_Data.setNews_pubdate(matcher.group(1));
			}
			pattern2 = Pattern.compile(REGEX_AUTHOR);
			matcher = pattern2.matcher(string2);
			if (matcher.find()) {
				// System.out.println(matcher.group(1));
				news_Data.setNews_author(matcher.group(1));
			}
			pattern2 = Pattern.compile(REGEX_ABSTRACT);
			matcher = pattern2.matcher(string2);
			if (matcher.find()) {
				String content = matcher.group(1);
				content = content.replaceAll("<.*?>", "");
				content = content.replaceAll("&nbsp;", "");
				content = content.replaceAll("]]", "");
				content = content.replaceAll(">", "");
				content = content.replaceAll("　", "");
				content = content.replaceAll("\\s", "");
				news_Data.setNews_text(content);
				if (content.length() > 100) {
					content = "        " + content.substring(0, 100);

				}
				// System.out.println(content);
				// System.out.println(matcher.group(1));
				news_Data.setNews_abstract(content);
			}
			news_Datas.add(news_Data);

		}
		// System.out.println(news_Datas.size());
		return news_Datas;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strings[] = { "时政", "国际", "财经", "金融", "体育", "法制", "教育", "文化", "社会", "娱乐", "观点", "汽车", "IT", "环保", "公益",
				"科技", "文史", "读书", "食品", "游戏", "家电", "房产", "健康", "党建", "人大", "军事", "旅游", "港澳" };
		// String strings[] = { "汽车" };
		Catalog_get catalog_get = new Catalog_get();
		MySQL_tools mySQL_tools = new MySQL_tools();
		for (String string : strings) {

			mySQL_tools.insertNews(string, catalog_get.Catalog_get(new News_URL().getNewsURL(string)));
		}
		mySQL_tools.close();
	}

}
