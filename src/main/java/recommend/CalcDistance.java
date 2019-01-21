package recommend;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.app.keyword.Keyword;

public class CalcDistance {
	String usrpersona;
	HashMap<String, Double> usrpersonamap;
	double L2;

	public CalcDistance(String usrpersona) {
		// TODO Auto-generated constructor stub
		this.usrpersona = usrpersona;
		usrpersonamap = new HashMap<String, Double>();
		Pattern pattern = Pattern.compile("\\{(.*?):(.*?)\\}");
		Matcher matcher = pattern.matcher(usrpersona);
		while (matcher.find()) {
			usrpersonamap.put(matcher.group(1), Double.valueOf(matcher.group(2)));
		}
		// calcL2();
		// System.out.println(usrpersonamap);
	}

	public double getdistance(List<Keyword> keywords) {
		HashMap<String, Double> keywordsmap = new HashMap<>();
		for (Keyword keyword : keywords) {
			keywordsmap.put(keyword.getName(), keyword.getScore());
		}
		double distance = 0;

		for (String string : usrpersonamap.keySet()) {
			if (keywordsmap.keySet().contains(string)) {
				distance = distance + Math.pow(usrpersonamap.get(string) - keywordsmap.get(string), 2);
				// System.out.println(string);
			} else {
				distance = distance + Math.pow(usrpersonamap.get(string), 2);
			}

		}
		// for (String string : keywordsmap.keySet()) {
		// if (usrpersonamap.keySet().contains(string)) {
		//
		// } else {
		// distance = distance + Math.pow(keywordsmap.get(string), 2);
		// }
		// }
		distance = Math.sqrt(distance);
		// double keywordsL2 = 0; //
		// 分母用L1范数就可以了，L2范数意义不大，而且吧，我觉得这个其实效果并不一定会很好，事实证明还是得用L2，L1很不准
		//
		// for (double d : keywordsmap.values()) {
		// keywordsL2 = keywordsL2 + Math.pow(d, 2);
		// }
		// keywordsL2 = Math.sqrt(keywordsL2);

		// distance = distance / L2 / keywordsL2;
		return distance;
	}

	// public void calcL2() {
	// L2 = 0;
	// for (double d : usrpersonamap.values()) {
	// L2 = L2 + Math.pow(d, 2);
	// }
	// L2 = Math.sqrt(L2);
	// }

	// public static void main(String[] args) {
	// CalcDistance calcDistance = new CalcDistance(
	// "{我们:62.2}{大家:56.5}{是否:3.2}{小米:5885.5}{荣耀:8878.3}{华为:2}{笔记本:32.32662}{林郑月娥:566.6}");
	// // System.out.println(Math.pow(8, 2));
	// List<Keyword> list = TFIDF.getTFIDE(
	// "在4月23日世界读书日到来之际，香港特区行政长官林郑月娥17日宣布，由2018至19学年(今年9月)起，特区政府会向全港公营中小学提供经常性津贴，以推广阅读文化，预计每年涉及经常性开支约4800万元(港币，下同)。当天上午，林郑月娥出席行政会议前会见传媒时谈及香港的教育发展。“教育开支是我们对未来发展最有意义的投资。”林郑月娥透露，目前，特区政府仍有约34亿元教育新资源等待分配。财政司司长陈茂波也在今年2月发表的财政预算案中，为许多教育工作预留了一次性开支，金额超过160亿元。在推动阅读风气方面，林郑月娥提出，由下学年起，为香港每家小学和中学每年分别提供4万元和7万元津贴。预计每年涉及经常性开支约4800万元，较在2016至17学年终止的广泛阅读计划津贴倍增。林郑月娥指出，阅读的好处非常多，既可带来喜悦、激发想像力，也可扩阔视野，“看书其实等于旅游，可以看到各地不同制度和文化特色。”希望此拨款计划能为学校提供额外资源，不单用来购买书本，更重要的是在学校安排推广阅读文化的活动。林郑月娥表示，未来推动香港阅读文化的工作，将会分别由特区政府教育局和康乐及文化事务署负责。下一步，特区政府也会与幼稚园业界商讨如何推广阅读，有需要时亦会提供额外资源。拨款计划公布后，香港中学校长会主席李雪英对此表示欢迎，认为特区政府重视推广阅读，希望特区政府亦在社区推广全民阅读文化。小学校长孔伟成亦欢迎拨款。他说，学校每年购买图书的开支约2至3万元。这项拨款计划可令校内新书数目倍增，校方亦可通过奖励计划，鼓励学生阅读。(记者李焯龙)",
	// 60);
	// System.out.println(calcDistance.getdistance(list));
	//
	// }

}
