package persona;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;

import gsy.MySQL_tools;
import gsy.News_Data;
import gsy.News_record;

/**
 * @author gsy 这个类每天凌晨调用，然后计算前一天阅读过新闻人的persona。
 *
 */
public class CalcPersona {

	public CalcPersona() {
		// TODO Auto-generated constructor stub
		MySQL_tools tools = new MySQL_tools();
		ArrayList<String> usrmaillist = tools.usremaillist();
		for (String string : usrmaillist) {
			ArrayList<News_record> news_records = tools.searchusrread(string);
			if (news_records.size() != 0) {
				String usrpersona = tools.searchusrpersona(string);
				if (usrpersona.length() != 0) {
					HashMap<String, Double> usrpersonamap = new HashMap<String, Double>();
					Pattern pattern = Pattern.compile("\\{(.*?):(.*?)\\}");
					Matcher matcher = pattern.matcher(usrpersona);
					while (matcher.find()) {
						usrpersonamap.put(matcher.group(1), Double.valueOf(matcher.group(2)));
					}
					for (String key : usrpersonamap.keySet()) {
						usrpersonamap.put(key, usrpersonamap.get(key) * 0.9); // 衰减，衰减系数为0.9
					}
					KeyWordComputer keyWordComputer = new KeyWordComputer(100);
					for (News_record news_record : news_records) {
						News_Data data = tools.searchnewsbylink(news_record.getChannel(), news_record.getNewslink());
						List<Keyword> temp = keyWordComputer.computeArticleTfidf(data.getNews_title(),
								data.getNews_text());
						Set<String> u = usrpersonamap.keySet();
						for (Keyword keyword : temp) {
							if (u.contains(keyword.getName())) {
								usrpersonamap.put(keyword.getName(),
										usrpersonamap.get(keyword.getName()) + keyword.getScore());
							} else {
								usrpersonamap.put(keyword.getName(), keyword.getScore());
							}
						}
					}
					LinkedHashMap<String, Double> mapd = (LinkedHashMap<String, Double>) sortByComparator(usrpersonamap,
							false);

					StringBuilder stringBuilder = new StringBuilder();
					for (String string2 : mapd.keySet()) {
						stringBuilder.append("{" + string2 + ":" + mapd.get(string2) + "}");
					}
					tools.updateusrpersona(string, stringBuilder.toString());

				} else {
					KeyWordComputer keyWordComputer = new KeyWordComputer(100);
					HashMap<String, Double> usrpersonamap = new HashMap<>();
					for (News_record news_record : news_records) {
						News_Data data = tools.searchnewsbylink(news_record.getChannel(), news_record.getNewslink());
						List<Keyword> temp = keyWordComputer.computeArticleTfidf(data.getNews_title(),
								data.getNews_text());
						Set<String> u = usrpersonamap.keySet();
						for (Keyword keyword : temp) {
							if (u.contains(keyword.getName())) {
								usrpersonamap.put(keyword.getName(),
										usrpersonamap.get(keyword.getName()) + keyword.getScore());
							} else {
								usrpersonamap.put(keyword.getName(), keyword.getScore());
							}
						}
					}
					StringBuilder stringBuilder = new StringBuilder();
					for (String string2 : usrpersonamap.keySet()) {
						stringBuilder.append("{" + string2 + ":" + usrpersonamap.get(string2) + "}");
					}
					tools.insertusrpersona(string, stringBuilder.toString());
				}
			}
		}
		tools.close();
	}

	public static void main(String[] args) {
		new CalcPersona();
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

}
