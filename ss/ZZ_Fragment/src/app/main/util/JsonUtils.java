package app.main.util;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;




import app.main.pojo.HomeWork;
import app.main.pojo.News;
import app.main.pojo.Parents;
import app.main.pojo.Tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
	Gson gson=new Gson();

	public LinkedList parseJson(String jsonData){
		Type listType=new TypeToken<LinkedList>(){}.getType();
		Gson gson=new Gson();
		LinkedList linkedList=gson.fromJson(jsonData, listType);
		return linkedList;
	}
	public Parents parseJsondata(String jsonData){
		Parents p=null;
		Type type=new TypeToken<Parents>() {
		}.getType();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();//Gson默认会使用系统环境的时间解析器来解析时间.显然en_US.UTF-8和zh_CN.UTF-8的默认时间不一致。修改代码如下，可以修复问题
		p=gson.fromJson(jsonData, type);

		return p;
	}
	public LinkedList<News> parseNewsJson(String jsonData){
		Type type=new TypeToken<LinkedList<News>>() {
		}.getType();
		Gson gson=new Gson();
		LinkedList<News> newsList=gson.fromJson(jsonData, type);
		
		return newsList;
		
	}
	public List<HomeWork> parseTask(String jsonData){
		Type type=new TypeToken<LinkedList<HomeWork>>() {
		}.getType();
		Gson gson=new Gson();
		List<HomeWork> newsList=gson.fromJson(jsonData, type);
		
		return newsList;
	
	}
/*	private List<HomeWork> parsejsonHomeWork(String jsonData){
		JSONArray array = JSONArray.getJSONObject(jsonData);       
		for(int i = 0; i < array.size(); i++){
            JSONObject jsonObject = array.getJSONObject(i);
            System.out.println(jsonObject.get("province"));
            System.out.println(jsonObject.get("city"));
            System.out.println(jsonObject.get("district"));
            System.out.println(jsonObject.get("address"));
        }
	}
	public static void main(String[] args) {
		[{"id":"12","coursename":"sdf","coursecontent":"sadf","createDate":"2014-05-29 00:00:00.0","finishtime":"2014-05-29 00:00:00.0"},{"id":"123","coursename":"语文","coursecontent":"完成课本p23","createDate":"2014-05-29 00:00:00.0","finishtime":"2014-05-29 00:00:00.0"},{"id":"124","coursename":"21","coursecontent":"21","createDate":"2014-05-29 00:00:00.0","finishtime":"2014-05-29 00:00:00.0"},{"id":"125","coursename":"24","coursecontent":"21","createDate":"2014-05-29 00:00:00.0","finishtime":"2014-05-29 00:00:00.0"},{"id":"126","coursename":"27","coursecontent":"21","createDate":"2014-05-29 00:00:00.0","finishtime":"2014-05-29 00:00:00.0"}]
	}*/
}
