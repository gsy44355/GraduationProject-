import java.util.ArrayList;

import gsy.News_Data;

/**
 * Created by gsy on 2018/3/18.
 */

public interface News_Server {
	public abstract ArrayList<News_Data> getNewsData(String type);

	public abstract String getNewstype();
}
