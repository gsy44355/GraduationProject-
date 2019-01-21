package record;

import java.util.HashMap;

public class News_URL {
	private HashMap<String, String> urlmap;

	public News_URL() {
		// TODO Auto-generated constructor stub
		urlmap = new HashMap<>();
		urlmap.put("时政", "http://www.people.com.cn/rss/politics.xml");
		urlmap.put("国际", "http://www.people.com.cn/rss/world.xml");
		urlmap.put("财经", "http://www.people.com.cn/rss/finance.xml");
		urlmap.put("金融", "http://www.people.com.cn/rss/money.xml");
		urlmap.put("体育", "http://www.people.com.cn/rss/sports.xml");
		urlmap.put("法制", "http://www.people.com.cn/rss/legal.xml");
		urlmap.put("教育", "http://www.people.com.cn/rss/legal.xml");
		urlmap.put("文化", "http://www.people.com.cn/rss/culture.xml");
		urlmap.put("社会", "http://www.people.com.cn/rss/society.xml");
		urlmap.put("娱乐", "http://www.people.com.cn/rss/ent.xml");
		urlmap.put("观点", "http://www.people.com.cn/rss/opinion.xml");
		urlmap.put("汽车", "http://www.people.com.cn/rss/auto.xml");
		urlmap.put("IT", "http://www.people.com.cn/rss/it.xml");
		urlmap.put("环保", "http://www.people.com.cn/rss/env.xml");
		urlmap.put("公益", "http://www.people.com.cn/rss/gongyi.xml");
		urlmap.put("科技", "http://www.people.com.cn/rss/scitech.xml");
		urlmap.put("文史", "http://www.people.com.cn/rss/history.xml");
		urlmap.put("读书", "http://www.people.com.cn/rss/book.xml");
		urlmap.put("食品", "http://www.people.com.cn/rss/shipin.xml");
		urlmap.put("游戏", "http://www.people.com.cn/rss/game.xml");
		urlmap.put("家电", "http://www.people.com.cn/rss/homea.xml");
		urlmap.put("房产", "http://www.people.com.cn/rss/house.xml");
		urlmap.put("健康", "http://www.people.com.cn/rss/health.xml");
		urlmap.put("党建", "http://www.people.com.cn/rss/dangjian.xml");
		urlmap.put("人大", "http://www.people.com.cn/rss/npc.xml");
		urlmap.put("政协", "http://www.people.com.cn/rss/cppcc.xml");
		urlmap.put("军事", "http://www.people.com.cn/rss/military.xml");
		urlmap.put("地方", "http://www.people.com.cn/rss/unn.xml");
		urlmap.put("旅游", "http://www.people.com.cn/rss/travel.xml");
		urlmap.put("舆情", "http://www.people.com.cn/rss/yuqing.xml");
		urlmap.put("港澳", "http://www.people.com.cn/rss/hm.xml");

	}

	public String getNewsURL(String channel) {
		return urlmap.get(channel);
	}

	// public static void main(String[] args) {
	// System.out.println(new News_URL().getNewsURL("人大"));
	// }

}
