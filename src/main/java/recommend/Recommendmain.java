package recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;

import gsy.MySQL_tools;
import gsy.News_Data;

public class Recommendmain {
	String string = null;
	final String regex = "<usremail>(.*?)</usremail>";
	final String necessary[] = { "时政", "国际", "金融", "社会", "IT", "科技", "人大", "军事" };
	final String normal[] = { "财经", "体育", "法制", "教育", "文化", "娱乐", "观点", "汽车", "环保", "公益", "文史", "读书", "食品", "游戏", "家电",
			"房产", "健康", "党建", "旅游", "港澳" };

	public Recommendmain(String string) {
		// TODO Auto-generated constructor stub
		this.string = string;
	}

	public String derecommend() {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		if (matcher.find()) {
			usremail = matcher.group(1);
		}
		MySQL_tools tools = new MySQL_tools();
		String usrfav = tools.searchusrinfo(usremail, "usrfav");
		String usrpersona = tools.searchusrpersona(usremail);
		// System.out.println("personalength" + usrpersona.length());
		// System.out.println(usrpersona);
		if (usrpersona.length() == 0) {
			ArrayList<News_Data> news_Datas = new ArrayList<>();
			String usrfavs[] = usrfav.split(",");
			if (usrfavs.length == 1) {
				for (String string : necessary) {
					if (usrfav.contains(string)) {

					} else {
						ArrayList<News_Data> templist = tools.searchnews(string, false);
						for (News_Data news_Data : templist) {
							news_Datas.add(news_Data);
						}
					}
				}
			}
			for (String s : usrfavs) {
				if (s.equals("推荐")) {
					continue;
				}
				ArrayList<News_Data> templist = tools.searchnews(s, false);
				for (News_Data news_Data : templist) {
					news_Datas.add(news_Data);
				}
			}
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
			// System.out.println("已经发送了");
			return stringBuilder.toString();

		} else {
			// System.out.println("为什么不执行？");
			CalcDistance calcDistance = new CalcDistance(usrpersona);
			KeyWordComputer keyWordComputer = new KeyWordComputer(100);
			ArrayList<News_Data> news_Datas = new ArrayList<>();
			String usrfavs[] = usrfav.split(",");
			// System.out.println(usrfavs);
			for (String s : usrfavs) {
				if (s.equals("推荐")) {
					continue;
				}
				ArrayList<News_Data> templist = tools.searchnews(s, false);
				for (News_Data news_Data : templist) {
					news_Datas.add(news_Data);
				}
			}
			for (String string : necessary) {
				if (usrfav.contains(string)) {

				} else {
					ArrayList<News_Data> templist = tools.searchnews(string, false);
					for (News_Data news_Data : templist) {
						news_Datas.add(news_Data);
					}
				}
			}
			// System.out.println("ok");
			// System.out.println(news_Datas.size());
			// System.out.println(normal.length);
			int randa = (int) (Math.random() * (normal.length - 1));
			// System.out.println(randa);
			int randb = -1;
			while (true) {
				randb = (int) (Math.random() * (normal.length - 1));
				// System.out.println(randb);
				if (randa != randb) {
					break;
				}

			}
			// System.out.println(randa);
			// System.out.println(randb);
			if (!usrfav.contains(normal[randa])) {
				ArrayList<News_Data> templist = tools.searchnews(normal[randa], false);
				for (News_Data news_Data : templist) {
					news_Datas.add(news_Data);
				}
			}
			if (!usrfav.contains(normal[randb])) {
				ArrayList<News_Data> templist = tools.searchnews(normal[randb], false);
				for (News_Data news_Data : templist) {
					news_Datas.add(news_Data);
				}
			}
			// System.out.println(news_Datas.size());
			// HashMap<String, List<Keyword>> calcmap = new
			// HashMap<String,List<Keyword>>();
			HashMap<String, News_Data> linkmap = new HashMap<String, News_Data>();
			HashMap<String, Double> scoremap = new HashMap<String, Double>();
			for (News_Data news_Data : news_Datas) {
				linkmap.put(news_Data.getNews_link(), news_Data);
				List<Keyword> temp = keyWordComputer.computeArticleTfidf(news_Data.getNews_title(),
						news_Data.getNews_text());
				// calcmap.put(news_Data.getNews_link(), temp);
				scoremap.put(news_Data.getNews_link(), calcDistance.getdistance(temp));
			}
			// System.out.println("可能是执行了吧");
			LinkedHashMap<String, Double> sort = (LinkedHashMap<String, Double>) sortByComparator(scoremap, true);
			ArrayList<String> newslinks = new ArrayList<String>(sort.keySet());
			ArrayList<News_Data> recommend = new ArrayList<News_Data>();
			int count = 0;
			double beita = 0;
			for (String string : newslinks) {
				count++;
				recommend.add(linkmap.get(string));
				sort.remove(string);
				if (count == 40) {
					beita = scoremap.get(string);
					break;
				}
			}
			ArrayList<String> newslinksrandom = new ArrayList<String>(sort.keySet());
			int[] rand = randomCommon(0, newslinksrandom.size(), 13);
			double lamuda = beita * (Math.random() * (0.1)); // 待定
			for (int i : rand) {
				if (scoremap.get(newslinksrandom.get(i)) - lamuda < beita) {
					recommend.add(linkmap.get(newslinksrandom.get(i)));
				}
			}
			StringBuilder stringBuilder = new StringBuilder();
			for (News_Data news_Data : recommend) {
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
			// System.out.println("已经发送了");
			return stringBuilder.toString();

		}
	}

	public static Map sortByComparator(Map unsortMap, boolean asc) {
		List list = new LinkedList(unsortMap.entrySet());
		// System.out.println("list:"+list);
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});
		if (asc) {
			Collections.reverse(list); // 由小到大,否则注释掉就可以了
		}
		Map sortedMap = new LinkedHashMap();

		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;

	}

	public static int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}

}
