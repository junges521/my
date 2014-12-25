package app.main.broad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.xmlpull.v1.XmlPullParser;

import android.net.Uri;
import android.util.Xml;
import app.main.pojo.News;
import app.main.util.Constant;
import app.main.util.HttpUtil;
import app.main.util.JsonUtils;
import app.main.util.MD5;

public class ContactService {
	/**
	 * 获取联系人数据
	 * 
	 * @return
	 * @throws Exception
	 */

	public static List<Map<String,Object>> getContacts() throws Exception {
		
		List<Map<String,Object>> newses = new ArrayList<Map<String,Object>>();
		JsonUtils jsonUtil=new JsonUtils();
		
		String jsonData=HttpUtil.queryStringForPost(Constant.SERVER_URL+"newsListAction","mobile");
		LinkedList<News> linkedList=jsonUtil.parseNewsJson(jsonData);
		for (Iterator<News> iterator = linkedList.iterator(); iterator.hasNext();) {
			News news=iterator.next();
			Map<String, Object> item1 = new HashMap<String, Object>();
			item1.put("list_title", news.getNewsName());
			item1.put("list_image",news.getNewsPhoto());
			item1.put("list_contect", news.getNewsContent());
			newses.add(item1);
		}
		return newses;
	}
	/**转化XML获取数据
	 * 服务器端的xml文件如下。。。。。。
	 * <?xml version="1.0" encoding="UTF-8"?>
		<contacts>
			<contact id="1">
				<name>Roco_1</name>
				<image src="http://192.168.1.100:8080/Hello/images/1.png" />
			</contact>
			.......
		</contacts>*/
	/*private static List<Map<String,News>> parseXML(InputStream inputStream)
			throws Exception {
		Map<String, Object> item1 = new HashMap<String, Object>();
		List<Map<String,News>> newses = new ArrayList<Map<String,News>>();
		News news = null;

		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(inputStream, "UTF-8");
		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_TAG:
				if ("News".equals(pullParser.getName())) {
					news = new News();
					news.setNewsId(new Integer(pullParser.getAttributeValue(0)));
				} else if ("name".equals(pullParser.getName())) {
					news.setNewsName(pullParser.nextText());
				} else if ("image".equals(pullParser.getName())) {
					news.setNewsPhoto(pullParser.getAttributeValue(0));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("News".equals(pullParser.getName())) {
					newses.add(news);
					news = null;
				}
			}
			event = pullParser.next();
		}
		return newses;
	}
*/
	/**
	 * 获取网络图片,如果图片存在于缓存中，就返回该图片，否则从网络中加载该图片并缓存起来
	 * 
	 * @param path
	 *            图片路径
	 * @return
	 */
	public static Uri getImage(String imagePath, File cacheDir)
			throws Exception {
		//缓存文件的文件名用MD5进行加密
		File localFile = new File(cacheDir, MD5.getMD5(imagePath)
				+ imagePath.substring(imagePath.lastIndexOf("."))); 
		if (localFile.exists()) {
			return Uri.fromFile(localFile);
		} else {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					imagePath).openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			//将文件缓存起来
			if (connection.getResponseCode() == 200) {
				FileOutputStream outputStream = new FileOutputStream(localFile);
				InputStream inputStream = connection.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
				inputStream.close();
				outputStream.close();
				return Uri.fromFile(localFile);
			}
		}
		return null;
	}
}
