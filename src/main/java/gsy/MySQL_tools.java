package gsy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import record.News_table;

public class MySQL_tools {
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/NEWSDB";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "123456";
	Connection conn = null;

	public MySQL_tools() {
		// TODO Auto-generated constructor stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 插入新闻，针对28张表
	public void insertNews(String channel, ArrayList<News_Data> news_Datas) {
		String lastURL = " ";
		try {
			Statement stms = conn.createStatement();
			String sql1;
			sql1 = "SELECT newslink FROM " + new News_table().getnewstable(channel) + " order by countid DESC";
			ResultSet rs = stms.executeQuery(sql1);
			if (rs.next()) {
				lastURL = rs.getString("newslink");
			}
			// 完成后关闭
			rs.close();
			stms.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = 0;
		for (j = 0; j < news_Datas.size(); j++) {
			if (lastURL.equals(news_Datas.get(j).getNews_link())) {
				break;
			}
		}
		String sql = "INSERT INTO " + new News_table().getnewstable(channel)
				+ " (newstitle, newslink, newsauthor,newsdate, newstext, newsabstract) VALUES (?,?,?,?,?,?);";
		// StringBuilder sb = new StringBuilder();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			for (int i = j - 1; i >= 0; i--) {

				stmt.setString(1, news_Datas.get(i).getNews_title());
				stmt.setString(2, news_Datas.get(i).getNews_link());
				stmt.setString(3, news_Datas.get(i).getNews_author());
				stmt.setString(4, news_Datas.get(i).getNews_pubdate());
				stmt.setString(5, news_Datas.get(i).getNews_text());
				stmt.setString(6, news_Datas.get(i).getNews_abstract());
				stmt.executeUpdate();
				// System.out.println(1);
			}
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// 查找新闻，从28张表返回若干条数据
	public ArrayList<News_Data> searchnews(String channel, boolean all) {
		int count;
		if (all) {
			count = Integer.MAX_VALUE;
		} else {
			count = 30;
		}

		ArrayList<News_Data> news_Datas = new ArrayList<News_Data>();
		try {
			Statement stms = conn.createStatement();
			String sql1;
			sql1 = "SELECT * FROM " + new News_table().getnewstable(channel) + " order by countid DESC";
			ResultSet rs = stms.executeQuery(sql1);
			while (rs.next()) {
				News_Data news_Data = new News_Data();
				news_Data.setNews_title(rs.getString("newstitle"));
				news_Data.setNews_link(rs.getString("newslink"));
				news_Data.setNews_class(channel);
				news_Data.setNews_author(rs.getString("newsauthor"));
				news_Data.setNews_pubdate(rs.getString("newsdate"));
				news_Data.setNews_text(rs.getString("newstext"));
				news_Data.setNews_abstract(rs.getString("newsabstract"));
				news_Datas.add(news_Data);
				count--;
				if (count == 0) {
					break;
				}
			}
			// 完成后关闭
			rs.close();
			stms.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news_Datas;
	}

	// 获取news信息，方便计算persona
	public News_Data searchnewsbylink(String channel, String newslink) {
		News_Data news_Data = new News_Data();
		try {
			Statement stms = conn.createStatement();
			String sql1;
			sql1 = "SELECT * FROM " + new News_table().getnewstable(channel) + " WHERE newslink= '" + newslink + "';";
			ResultSet rs = stms.executeQuery(sql1);
			if (rs.next()) {

				news_Data.setNews_title(rs.getString("newstitle"));
				news_Data.setNews_link(rs.getString("newslink"));
				news_Data.setNews_class(channel);
				news_Data.setNews_author(rs.getString("newsauthor"));
				news_Data.setNews_pubdate(rs.getString("newsdate"));
				news_Data.setNews_text(rs.getString("newstext"));
				news_Data.setNews_abstract(rs.getString("newsabstract"));

			}
			// 完成后关闭
			rs.close();
			stms.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news_Data;
	}

	// 用户信息表增加信息
	public int insertuserinfo(String usremail, String usrpsw, String usrtel, String usrfav) {
		String sql = "INSERT INTO  USRINFO (usremail, usrpsw, usrtel ,usrfav) VALUES (?,?,?,?);";
		// StringBuilder sb = new StringBuilder();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, usremail);
			stmt.setString(2, usrpsw);
			stmt.setString(3, usrtel);
			stmt.setString(4, usrfav);
			stmt.executeUpdate();

			stmt.close();
			return 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return -1;
		}

	}

	// 查找usrifo表，比对密码或喜爱列表 what
	/**
	 * @param usreamail
	 * @param what
	 *            只有两个选项 一个是usrpsw 一个是usrfav
	 * @return
	 * 
	 */
	public String searchusrinfo(String usreamail, String what) {
		String usrpsw = "";
		try {
			Statement stms = conn.createStatement();
			String sql1;
			sql1 = "SELECT " + what + " FROM USRINFO WHERE USREMAIL='" + usreamail + "'";
			ResultSet rs = stms.executeQuery(sql1);
			if (rs.next()) {
				usrpsw = rs.getString(what);
			}
			// 完成后关闭
			rs.close();
			stms.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usrpsw;
	}

	// 修改usrinfo表，针对的是忘记密码
	public int updateusrinfo(String usremail, String usrpsw) {
		String sql = "update usrinfo set usrpsw=? where usremail=?";
		PreparedStatement ptmt;
		try {
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, usrpsw);
			ptmt.setString(2, usremail);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;

	}

	// 修改usrinfo表，针对的是更新喜爱列表
	public int updateusrinfofav(String usremail, String usrfav) {
		String sql = "update usrinfo set usrfav=? where usremail=?";
		PreparedStatement ptmt;
		try {
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, usrfav);
			ptmt.setString(2, usremail);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	// 增加用户阅读记录
	public void insertusrread(String usremail, String newsclass, String newslink, String date, int flag) {
		String sql = "INSERT INTO  USRREAD (usremail, newsclass, newslink, date, flag) VALUES (?,?,?,?,?);";
		PreparedStatement ptmt;
		try {
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, usremail);
			ptmt.setString(2, newsclass);
			ptmt.setString(3, newslink);
			ptmt.setString(4, date);
			ptmt.setInt(5, flag);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("用户重复增加阅读记录");

		}

	}

	// 获取用户email信息
	public ArrayList<String> usremaillist() {
		ArrayList<String> emaillist = new ArrayList<String>();
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// // System.out.println(df.format(System.currentTimeMillis()));
		// String da = df.format(System.currentTimeMillis()-86400000);
		try {
			Statement stms = conn.createStatement();
			String sql1;
			sql1 = "SELECT usremail FROM USRINFO";
			ResultSet rs = stms.executeQuery(sql1);
			while (rs.next()) {

				emaillist.add(rs.getString("usremail"));

			}
			// 完成后关闭
			rs.close();
			stms.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emaillist;
	}

	// 获取用户阅读记录，用来更新用户画像，每天只获取当天的进行更新
	public ArrayList<News_record> searchusrread(String usremail) {
		ArrayList<News_record> news_records = new ArrayList<News_record>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(df.format(System.currentTimeMillis()));
		String da = df.format(System.currentTimeMillis());
		// System.out.println(da);
		try {
			Statement stms = conn.createStatement();
			String sql1;
			sql1 = "SELECT * FROM USRREAD WHERE USREMAIL='" + usremail + "'  AND DATE ='" + da + "'";
			ResultSet rs = stms.executeQuery(sql1);
			while (rs.next()) {
				// System.out.println(1);
				News_record news_record = new News_record();
				news_record.setChannel(rs.getString("newsclass"));
				news_record.setNewslink(rs.getString("newslink"));
				news_record.setFlag(rs.getInt("flag"));
				news_record.setDate(rs.getString("date"));
				news_records.add(news_record);

			}
			// 完成后关闭
			rs.close();
			stms.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news_records;
	}

	// 增加用户画像
	public void insertusrpersona(String usremail, String usrpersona) {
		String sql = "INSERT INTO  USRPERSONA (usremail,usrpersona) VALUES (?,?);";
		PreparedStatement ptmt;
		try {
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, usremail);
			ptmt.setString(2, usrpersona);

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	// 查询用户画像
	public String searchusrpersona(String usremail) {
		String usrpersona = "";
		try {
			Statement stms = conn.createStatement();
			String sql1;
			sql1 = "SELECT usrpersona FROM USRPERSONA WHERE USREMAIL='" + usremail + "'";
			ResultSet rs = stms.executeQuery(sql1);
			if (rs.next()) {
				usrpersona = rs.getString("usrpersona");
			}
			// 完成后关闭
			rs.close();
			stms.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usrpersona;
	}

	// 修改用户画像
	public void updateusrpersona(String usremail, String usrpersona) {
		String sql = "update USRPERSONA set usrpersona=? where usremail=?";
		PreparedStatement ptmt;
		try {
			ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1, usrpersona);
			ptmt.setString(2, usremail);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	// 关闭connection
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
