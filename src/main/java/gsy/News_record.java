package gsy;

public class News_record {
	String usremail;
	String channel;
	String newslink;
	String date;
	int flag;

	public String getUsremail() {
		return usremail;
	}

	public void setUsremail(String usremail) {
		this.usremail = usremail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getNewslink() {
		return newslink;
	}

	public void setNewslink(String newslink) {
		this.newslink = newslink;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public News_record() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "News_record [usremail=" + usremail + ", channel=" + channel + ", newslink=" + newslink + ", date="
				+ date + ", flag=" + flag + "]";
	}

}
