package app.main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static final String BASE_URL="http://118.212.152.245:8080/dwzStruts/";
	
	public static HttpGet getHttpGet(String url){
		HttpGet request = new HttpGet(url);
		 return request;
	}
	
	public static HttpPost getHttpPost(String url){
		 HttpPost request = new HttpPost(url);
		 return request;
	}
	
	public static HttpResponse getHttpResponse(HttpGet request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	
	public static String queryStringForPost(String url,String newsCategory){
		HttpPost request = HttpUtil.getHttpPost(url);
		 List<NameValuePair> params = new  ArrayList<NameValuePair>();  

				                 params.add(new BasicNameValuePair("op",newsCategory));  

				                 //  设置HTTP POST请求参数  

				                 try {
									request.setEntity(new  UrlEncodedFormEntity(params, HTTP.UTF_8));
								} catch (UnsupportedEncodingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}  


		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				System.out.println(result);
				
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return null;
		}
        return null;
    }
	public static String queryForPost(String url,String createDate){
		HttpPost request = HttpUtil.getHttpPost(url);
		 List<NameValuePair> params = new  ArrayList<NameValuePair>();  

				                 params.add(new BasicNameValuePair("cd",createDate));  

				                 //  设置HTTP POST请求参数  

				                 try {
									request.setEntity(new  UrlEncodedFormEntity(params, HTTP.UTF_8));
								} catch (UnsupportedEncodingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}  


		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				System.out.println(result);
				
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return null;
		}
       return null;
   }
	
	public static String queryStringForPost(String url){
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				System.out.println(result);
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return null;
		}
        return null;
    }
	
	public static String queryStringForPost(HttpPost request){
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
        return null;
    }
	
	public static  String queryStringForGet(String url){
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
        return null;
    }
	
	public String HttpClientForPost(String URL){
		 BufferedReader in = null;  
		  StringBuffer string = new StringBuffer(""); 
	        try {  
	            HttpClient client = new DefaultHttpClient();  
	            HttpPost request = new HttpPost(URL);  
	            //使用NameValuePair来保存要传递的Post参数  
	            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();  
	            //添加要传递的参数    
	            postParameters.add(new BasicNameValuePair("id", "12345"));  
	            postParameters.add(new BasicNameValuePair("username", "dave"));  
	            //实例化UrlEncodedFormEntity对象  
	            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(  
	                    postParameters);  
	 
	            //使用HttpPost对象来设置UrlEncodedFormEntity的Entity  
	            request.setEntity(formEntity);  
	            HttpResponse response = client.execute(request);  
	            in = new BufferedReader(  
	                    new InputStreamReader(  
	                            response.getEntity().getContent()));  
	 
	           
	            String lineStr = "";  
	            while ((lineStr = in.readLine()) != null) {  
	                string.append(lineStr + "\n");  
	            }  
	            in.close();  
	 
	            String resultStr = string.toString();  
	            System.out.println(resultStr);  
	        } catch(Exception e) {  
	            // Do something about exceptions  
	        } finally {  
	            if (in != null) {  
	                try {  
	                    in.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }
			return string.toString(); 
	}
	public static void main(String[] args) {
		System.out.println(queryForPost("http://192.168.191.1:8080/dwzStruts/dwz/gethomeWorksAction.action",null));
	}
}
