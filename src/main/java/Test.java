import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Date date = new Date();

		// 获取本地时间然后干个什么事，之后考虑
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(df.format(System.currentTimeMillis() - 86400000));
		// System.out.println(System.currentTimeMillis());
		// Set<String> expectedNature = new HashSet<String>() {

		// {
		// add("n");
		// add("v");
		// add("vd");
		// add("vn");
		// add("vf");
		// add("vx");
		// add("vi");
		// add("vl");
		// add("vg");
		// add("nt");
		// add("nz");
		// add("nw");
		// add("nl");
		// add("ng");
		// add("userDefine");
		// add("wh");
		// }
		// };
		// String str =
		// "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
		// Result result = ToAnalysis.parse(str); //
		// 分词结果的一个封装，主要是一个List<Term>的terms
		// System.out.println(result.getTerms());
		//
		// List<Term> terms = result.getTerms(); // 拿到terms
		// System.out.println(terms.size());
		//
		// for (int i = 0; i < terms.size(); i++) {
		// String word = terms.get(i).getName(); // 拿到词
		// String natureStr = terms.get(i).getNatureStr(); // 拿到词性
		// if (expectedNature.contains(natureStr)) {
		// System.out.println(word + ":" + natureStr);
		// }
		// }
		// String string = "CREATE TABLE `shizheng` (\n`newslink` varchar(100)
		// NOT NULL,\n`newstitle` text,\n `newsauthor` varchar(100) DEFAULT
		// NULL,\n `newsdate` varchar(40) DEFAULT NULL,\n `newstext` text,\n
		// `newsabstract` text,\n PRIMARY KEY (`newslink`)\n) ENGINE=InnoDB
		// DEFAULT CHARSET=utf8;";
		// String string = "ALTER TABLE `shizheng`\nADD COLUMN `countid` int
		// NULL AUTO_INCREMENT AFTER `newsabstract`;";

		// String string = "CREATE TABLE `zzzz` (\n `countid` int(11) NOT NULL
		// AUTO_INCREMENT,\n `newslink` varchar(100) NOT NULL,\n `newstitle`
		// text,\n `newsauthor` varchar(100) DEFAULT NULL,\n `newdate`
		// varchar(40) DEFAULT NULL,\n `newstext` text,\n `newabstract` text,\n
		// PRIMARY KEY (`countid`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		// String[] dStrings = { "shizheng", "guoji", "caijing", "jinrong",
		// "tiyu", "fazhi", "jiaoyu", "wenhua", "shehui",
		// "yule", "guandian", "qiche", "it", "huanbao", "gongyi", "keji",
		// "wenshi", "dushu", "shipin", "youxi",
		// "jiadian", "fangchan", "jiankang", "dangjian", "renda", "junshi",
		// "lvyou", "gangao" };
		// for (String iString : dStrings) {
		// System.out.println(string.replace("zzzz", iString));
		// // // }
		// //
		// MySQL_tools tools = new MySQL_tools();
		// String fav = "金融,时政,旅游,科技";
		// String usrpersona =
		// "{中国:56}{科技:80}{华为:25}{谈判:55}{战争:23}{习近平:100}{有效:1}{效果:23}{电脑:23}{和平:40}{核弹:15}{美丽:12}{短发：23}{平衡:23}{会晤:22}";
		// // String em = "443554017@qq.com";
		// // tools.insertuserinfo("443555017@qq.com",
		// // "bc129328da78f150af2e1b839e4ade55", "18117835413", fav);
		// for (int i = 0; i < 10; i++) {
		// tools.insertuserinfo("443554" + i + "17@qq.com",
		// "bc129328da78f150af2e1b839e4ade55", "18117835413", fav);
		// }
		// for (int i = 0; i < 5; i++) {
		// for (int j = 0; j < 10; j++) {
		// tools.insertusrread("443554" + i + "17@qq.com", "金融",
		// "http://money.people.com.cn/n1/2018/0426/c42877-29952848.html" + j,
		// "2018-04-25", 1);
		// }
		// }
		// for (int i = 0; i < 10; i++) {
		// tools.insertusrpersona("443554" + i + "17@qq.com", usrpersona);
		// }
		// tools.insertusrread("443554017@qq.com", "时政",
		// "http://politics.people.com.cn/n1/2018/0424/c1001-29947749.html",
		// 1);
		// tools.insertusrpersona("443554017@qq.com", usrpersona);
		// ArrayList<News_Data> datas = tools.searchnews("时政", true);
		// for (News_Data news_Data : datas) {
		// System.out.println(news_Data.getNews_title());
		// }
		// System.out.println(tools.searchusrinfo("443554014@qq.com",
		// "usrfav"));
		// System.out.println(tools.searchusrpersona("443554017@qq.com"));
		// ArrayList<News_record> news_records = (ArrayList<News_record>)
		// tools.searchusrread("443554014@qq.com");
		// for (News_record news_record : news_records) {
		// System.out.println(news_record.toString());
		// }
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// // System.out.println(df.format(System.currentTimeMillis()));
		// String da = df.format(System.currentTimeMillis() - 86400000);
		// System.out.println("2018-04-26".equals(da));
		// tools.updateusrinfo("443554017@qq.com", "1256484894684981651");
		// tools.updateusrinfo("443554014@qq.com", "46548fwef");
		// tools.updateusrinfofav("443554114@qq.com", "金融，保险，信息");
		// tools.updateusrpersona("443554014@qq.com", "更新");
		// ArrayList<String> arrayList = tools.usremaillist();
		// System.out.println(arrayList);
		// Map<String, Double> map = new HashMap<String, Double>();
		// map.put("qqq", 1.0);
		// map.put("www", 2.0);
		// map.put("eee", 3.0);
		// map.put("rrr", 4.0);
		// map.put("ttt", 5.0);
		// System.out.println(map);
		// LinkedHashMap<String, Double> map2 = (LinkedHashMap<String, Double>)
		// Recommendmain.sortByComparator(map);
		// System.out.println(map2);
		// ArrayList<String> set = new ArrayList(map2.keySet());
		// System.out.println(set);
		// for (String string : set) {
		// System.out.println(string + ":::::" + map2.get(string));
		// }
		// String string =
		// "<usremail>443554017@qq.com</usremail><newslink>http://env.people.com.cn/n1/2018/0421/c1010-29940942.html</newslink><newsclass>环保</newsclass><flag>1</flag>";
		// // Recommendmain recommendmain = new Recommendmain(string);
		// // Usrfunction usrfunction = new Usrfunction(string);
		// // Sendidentfyingcode sendidentfyingcode = new
		// // Sendidentfyingcode(string);
		// Newsgain newsgain = new Newsgain(string);
		// String string2 = newsgain.setreadnews();
		// System.out.println(string2);
		// String s = "443554017@qq.com";

		// System.out.println(s.matches(".+?@.+?"));
		// for (int i = 0; i < 300; i++) {
		// System.out.println((int) (1 + Math.random() * 100));
		// }
		// String chooselist[] = { "qwe", "qewqw", "12313" };
		// StringBuilder stringBuilder = new StringBuilder();
		// for (String s : chooselist) {
		// stringBuilder.append(s);
		// stringBuilder.append(",");
		//
		// }
		// stringBuilder.delete(stringBuilder.lastIndexOf(","),
		// stringBuilder.length());
		// System.out.println(stringBuilder.toString());
		String string = "";
		URL url = null;
		try {
			url = new URL("http://www.people.com.cn/rss/auto.xml");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 3.0.04506)");
			InputStream content = conn.getInputStream();
			StringBuilder out = new StringBuilder();
			byte[] b = new byte[4096];
			for (int n; (n = content.read(b)) != -1;) {
				out.append(new String(b, 0, n, "UTF-8"));
			}
			string = out.toString();
			System.out.println(string);
			File writef = new File("");
			String writefile = "测试文档.txt";// 创建四个字符串类型，捕获字符串名然后创建文件对象。
			writef = new File(writefile);
			if (!writef.exists()) {
				try {
					writef.createNewFile();
				} catch (IOException e) {
					System.out.println("创建失败");
				}
			}

			Writer outt = new FileWriter(writef, false);
			BufferedWriter out1 = new BufferedWriter(outt);
			String s = null;

			String outstring;
			outstring = string;
			// outstring = trans.zhushi_2(outstring,in);
			if (outstring.length() >= 1) {
				out1.write(outstring);
				out1.newLine();
			}

			System.out.println("输出完成！");

			out1.flush();
			out1.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
