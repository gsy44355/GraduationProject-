package record;

import java.util.HashMap;

public class News_table {
	HashMap<String, String> tablemap;

	public News_table() {
		// TODO Auto-generated constructor stub
		tablemap = new HashMap<String, String>();
		tablemap.put("时政", "shizheng");
		tablemap.put("国际", "guoji");
		tablemap.put("财经", "caijing");
		tablemap.put("金融", "jinrong");
		tablemap.put("体育", "tiyu");
		tablemap.put("法制", "fazhi");
		tablemap.put("教育", "jiaoyu");
		tablemap.put("文化", "wenhua");
		tablemap.put("社会", "shehui");
		tablemap.put("娱乐", "yule");
		tablemap.put("观点", "guandian");
		tablemap.put("汽车", "qiche");
		tablemap.put("IT", "it");
		tablemap.put("环保", "huanbao");
		tablemap.put("公益", "gongyi");
		tablemap.put("科技", "keji");
		tablemap.put("文史", "wenshi");
		tablemap.put("读书", "dushu");
		tablemap.put("食品", "shipin");
		tablemap.put("游戏", "youxi");
		tablemap.put("家电", "jiadian");
		tablemap.put("房产", "fangchan");
		tablemap.put("健康", "jiankang");
		tablemap.put("党建", "dangjian");
		tablemap.put("人大", "renda");
		tablemap.put("军事", "junshi");
		tablemap.put("旅游", "lvyou");
		tablemap.put("港澳", "gangao");

	}

	public String getnewstable(String channel) {
		return tablemap.get(channel);
	}

	// public static void main(String[] args) {
	// System.out.println(new News_table().getnewstable("人大"));
	// }

}
