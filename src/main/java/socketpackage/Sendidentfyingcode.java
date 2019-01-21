package socketpackage;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import record.Identifying_Code;

public class Sendidentfyingcode {
	String string;
	private int identitycode = 000000;
	public static String receiveMailAccount = "";// main方法测试使用的收件箱

	public Sendidentfyingcode(String string) {
		// TODO Auto-generated constructor stub
		this.string = string;
	}

	public String sendemail() {
		Pattern pattern = Pattern.compile("<usremail>(.*?)</usremail><identfyingcode>(.*?)</identfyingcode>");
		Matcher matcher = pattern.matcher(string);
		String usremail = null;
		String rand = "";
		if (matcher.find()) {
			usremail = matcher.group(1);
			rand = matcher.group(2);
		}
		identitycode = new Identifying_Code().getIdentifyingcode(rand);
		receiveMailAccount = usremail;
		try {
			sendemail1();
			System.out.println("发送验证码成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "发送失败";
		}
		return "发送成功";
	}

	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）工程下载下来需要修改的
	public static String myEmailAccount = "";
	public static String myEmailPassword = "";

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.163.com";

	// 收件人邮箱（替换为自己知道的有效邮箱）

	public void sendemail1() throws Exception {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 请求认证，参数名称与具体实现有关
		// props.setProperty("mail.smtp.port", "587"); //设置端口号
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		// session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		// 3. 创建一封邮件
		MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);
		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();
		// 5. 使用 邮箱账号 和 密码 连接邮件服务器
		// 这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
		transport.connect(myEmailAccount, myEmailPassword);
		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人,
		// 抄送人, 密送人
		transport.sendMessage(message, message.getAllRecipients());
		// 7. 关闭连接
		transport.close();
	}

	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */

	public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, "新闻个性化随机推荐系统验证码", "UTF-8"));
		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "G", "UTF-8"));
		// message.Headers.Add("X-Mailer", "Microsoft Outlook Express
		// 6.00.2900.2869");
		// message.setHeader("X-Mailer", "Microsoft Outlook Express
		// 6.00.2900.2869");
		// 4. Subject: 邮件主题
		message.setSubject("新闻个性化随机推荐系统验证码", "UTF-8");
		String string = "<div>	"
				+ "    <div style=\"FONT-SIZE: 11pt\">                                                                                                                                    "
				+ "        亲爱的用户，您好！                                                                                                                                             "
				+ "    </div>                                                                                                                                                             "
				+ "    <br  />                                                                                                                                                            "
				+ "    <div style=\"FONT-SIZE: 11pt\">                                                                                                                                    "
				+ "        个性化随机新闻推荐系统系统，本次请求的验证码为：                                                                                                                 "
				+ "    </div>                                                                                                                                                             "
				+ "    <br  />                                                                                                                                                            "
				+ "    <br  />                                                                                                                                                            "
				+ "    <div>                                                                                                                                                              "
				+ "        <span style=\"COLOR: #0094ff; FONT-SIZE: 40pt\">" + identitycode
				+ "</span>                                                                                                  "
				+ "        <span style=\"COLOR: #ff0000; FONT-SIZE: 11pt\">(为了保障您帐号的安全性，请在1小时内完成验证。)</span>                                                         "
				+ "    </div>                                                                                                                                                             "
				+ "    <br  />                                                                                                                                                            "
				+ "    <br  />                                                                                                                                                            "
				+ "    <hr style=\"BORDER-BOTTOM: #808080 0px dashed; BORDER-LEFT: #808080 0px dashed; HEIGHT: 1px; BORDER-TOP: #808080 1px dashed; BORDER-RIGHT: #808080 0px dashed\"  />"
				+ "    <br  />                                                                                                                                                            "
				+ "    <div style=\"COLOR: #808080\">                                                                                                                                     "
				+ "        此邮件由系统自动发出，系统不接受回信，因此请勿直接回复。                                                                                                       "
				+ "        <br  /> 安全使用您的个性化随机推荐系统的注意事项：                                                                                                                     "
				+ "        <br  /> 1、请不要在其他网站上使用相同的邮箱和密码进行注册。                                                                                                    "
				+ "        <br  /> 2、请不要告知任何人您的个性化随机新闻推荐系统密码信息，包括个性化随机推荐系统的工作人员。                                                                                   "
				+ "        <br  />                                                                                                                                                        "
				+ "        <br  /> 如果您错误的收到本电子邮件，请您忽略上述内容。                                                                                                         "
				+ "    </div>                                                                                                                                                             "
				+ "    <br  />                                                                                                                                                            "
				+ "    <hr style=\"BORDER-BOTTOM: #808080 0px dashed; BORDER-LEFT: #808080 0px dashed; HEIGHT: 1px; BORDER-TOP: #808080 1px dashed; BORDER-RIGHT: #808080 0px dashed\"  />"
				+ "    <div>                                                                                                                                                              "
				+ "        <br  />                                                                                                                                                        "
				+ "    </div>                                                                                                                                                             "
				+ "    <div style=\"TEXT-ALIGN: right; FONT-SIZE: 11pt\">                                                                                                                 "
				+ "        GSY                                                                                                                                                        "
				+ "    </div>                                                                                                                                                             "
				+ "</div>                                                                                                                                                                 ";
		// 5. Content: 邮件正文（可以使用html标签），html标签有用，\n没有用
		message.setContent(string, "text/html;charset=UTF-8");
		// 6. 设置发件时间
		message.setSentDate(new Date());
		// 7. 保存设置
		message.saveChanges();
		return message;
	}
}
