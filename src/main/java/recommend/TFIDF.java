package recommend;
/**
 * 
 */

import java.util.List;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;

public class TFIDF {
	public static Result split(String text) {
		return ToAnalysis.parse(text);
	}

	/**
	 * 
	 * @param title
	 *            文本标题
	 * @param content
	 *            文本内容
	 * @param keyNums
	 *            返回的关键词数目
	 * @return
	 */
	public static List<Keyword> getTFIDE(String title, String content, int keyNums) {
		// String
		// sentence="我今天很开心，所以一口气买了好多东西。然而我一不小心把本月预算透支了，现在有很不开心了，因为后面的日子得吃土了！";
		KeyWordComputer kwc = new KeyWordComputer(100);

		return kwc.computeArticleTfidf(title, content);
	}

	/**
	 * 
	 * @param content
	 *            文本内容
	 * @param keyNums
	 *            返回的关键词数目
	 * @return
	 */
	public static List<Keyword> getTFIDE(String content, int keyNums) {
		KeyWordComputer kwc = new KeyWordComputer(keyNums);

		return kwc.computeArticleTfidf(content);
	}

	public static void main(String[] args) {
		List<Keyword> list = getTFIDE("我科学家用遥感首次发现国外丝路考古遗址",
				"人民网北京4月20日电（赵竹青）记者从中科院遥感地球所获悉，我国科学家利用空间考古技术在丝绸之路西端突尼斯发现了10处古罗马时期的考古遗存。这是中国科学家利用遥感技术在中国境外首次发现考古遗址，这些遗存揭示了古罗马时期南线军事防御系统的布局与农业灌溉系统的结构。19日，“数字丝路”国际科学计划世界遗产工作组在突尼斯首都突尼斯市举行“一带一路”遥感考古新闻发布会，宣布由中科院遥感地球所研究员王心源带领的空间考古研究团队，联合突尼斯、意大利、巴基斯坦的科学家，利用空间考古技术与方法，完成了这次考古发现。据王心源介绍，此次联合考古历经两年多。2016年1月先在北京做室内遥感图像处理及解译分析，然后分别于2017年4月、11月和2018年4月与当地考古专家联合进行实地调研与验证，最后在突尼斯南部确定新发现了10处古罗马时期遗存，包括边墙3段（Limes）、军事堡垒（Forts）2个，以及农业灌溉系统1处、水窖3处、墓葬1处。这些考古遗存反映出古罗马时期帝国南部边疆的军事防御体系。其中，边墙与堡垒用于防守和保护边疆，阻挡来自南部和西部的游牧民的侵扰；农业灌溉系统以及储存淡水的水窖用于保障边疆军民的粮食生产与生活需要。突尼斯位于古代海上丝绸之路的西端，是古罗马时期重要的海上贸易港口枢纽。此次发现对于研究古罗马时期军事防御系统、农业灌溉系统，以及丝绸之路西端线路走向、古绿洲变迁、环境变化及其影响具有重要意义。此次利用遥感观测技术、卫星导航系统、地理信息分析系统等综合技术，结合文献分析、实地调研开展的空间考古技术与方法，是中国科学家走出国门并首次主导联合亚、欧、非相关国家开展空间考古遗存发现与系统研究，这对于提升中国科学家在“一带一路”沿线开展国际合作研究水平，形成空间考古学科一套新的研究技术与方法范式具有重要的标志性意义。据了解，遥感技术在干旱区考古调查中具有独特的技术优势和广泛的应用前景，是考古学发展的新增长点。该方法是利用搭载在卫星、航天飞机、飞机、飞船、飞艇等空间平台上的各类传感器来记录地物目标与电磁波相互作用的特性，根据地貌形态、地物阴影、植被及土壤湿度、霜雪等多种因素在目标地区所形成的不同标志，解释地面或地下遗迹的影像特征。遥感考古具有全局、直观、周期性强、覆盖范围大的特点；相对传统的野外考古而言，也具有成本优势，能节省人力物力；并且在环境变迁与人类活动关系的研究中发挥更大作用。遥感考古与考古学、历史学、地学等的有效结合，能够为丝绸之路古城镇、古绿洲、古水系、古道路，以及区域社会经济文化变迁的研究，提供科学的手段。目前，中科院遥感与数字地球研究所空间考古技术参与了柬埔寨吴哥窟的遗产保护并取得重要成果，同时还与泰国、巴基斯坦、缅甸等国正在开展遥感考古合作。",
				60);
		// Keyword ke1 = list.get(0);
		for (Keyword keyword : list) {
			// System.out.println(keyword.getName());
			System.out.println(keyword);
			// System.out.println(keyword.getFreq());
			// System.out.println(keyword.getScore());
			// System.out.println(keyword.compareTo(ke1));
		}

	}
}
