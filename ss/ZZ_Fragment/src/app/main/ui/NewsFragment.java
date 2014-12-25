package app.main.ui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.umeng.analytics.MobclickAgent;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.main.jxt.R;

public class NewsFragment extends Fragment {

	ImageView lv_left;
	ImageView iv_right;
	TextView news_title;//新闻标题
	TextView news_date;//时间
	TextView news_from;//来源
	TextView news_content;//新闻内容
	TextView news_editor;//编辑人
	TextView tv_mainpage;
	
	 private static Bundle bundle;  
     
	    static NewsFragment newInstance(Bundle b){  
	    	NewsFragment newsFragment = new NewsFragment();  
	        bundle = b;  
	        return newsFragment;  
	          
	    }  
	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return super.getView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mView = inflater.inflate(R.layout.schoolnews, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage=(TextView) mView.findViewById(R.id.tv_mainpage);
		
		news_title=(TextView) mView.findViewById(R.id.news_title);
		news_date=(TextView) mView.findViewById(R.id.news_date);
		news_from=(TextView) mView.findViewById(R.id.news_from);
		news_content=(TextView) mView.findViewById(R.id.news_content);
		news_editor=(TextView) mView.findViewById(R.id.news_editor);
		
		return mView;
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		news_title.setText(NewsFragment.bundle.get("list_title").toString());
		tv_mainpage.setText(getResources().getString(R.string.school_news));
		lv_left.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showLeft();
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
			}
		});	
		
		AsyncNews asyncNews=new AsyncNews(getActivity());
		//asyncNews.execute(params);//进行异步操作
		
		super.onActivityCreated(savedInstanceState);
	}

private class AsyncNews extends AsyncTask<String, Integer, String> {

		
		ProgressDialog pdialog;//产生进度条对话框
		/*
		 * 为了正确的使用AsyncTask类，以下是几条必须遵守的准则： 
　　1) Task的实例必须在UI thread中创建 
　　2) execute方法必须在UI thread中调用 
　　3) 不要手动的调用onPreExecute(), onPostExecute(Result)，doInBackground(Params...), onProgressUpdate(Progress...)这几个方法 
　　4) 该task只能被执行一次，否则多次调用时将会出现异常 
      doInBackground方法和onPostExecute的参数必须对应，这两个参数在AsyncTask声明的泛型参数列表中指定，第一个为doInBackground接受的参数，第二个为显示进度的参数，第第三个为doInBackground返回和onPostExecute传入的参数。
		 * 
		 */
		
		
		public AsyncNews(Context context) {
			/*pdialog = new ProgressDialog(context, 0);   
            pdialog.setButton("取消", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int i) {
              dialog.cancel();
             }
            });
            pdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
             public void onCancel(DialogInterface dialog) {
              dialog.dismiss();
             }
            });
            pdialog.setCancelable(true);
            pdialog.setMax(100);
            pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pdialog.show();*/
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stu
			super.onPostExecute(result);
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try{

				HttpClient client = new DefaultHttpClient();
				// params[0]代表连接的url
				// HttpGet get = new HttpGet(params[0]);
				HttpPost httpPost = new HttpPost(params[0]);
				List<NameValuePair> param = new ArrayList<NameValuePair>();

				param.add(new BasicNameValuePair("op", "mobile"));
				httpPost.setEntity(new UrlEncodedFormEntity(param, "utf-8"));
				HttpResponse response = client.execute(httpPost);
				HttpEntity entity = response.getEntity();
				long length = entity.getContentLength();
				InputStream is = entity.getContent();
				String s = null;
				if (is != null) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					byte[] buf = new byte[128];

					int ch = -1;

					int count = 0;

					while ((ch = is.read(buf)) != -1) {

						baos.write(buf, 0, ch);

						count += ch;

						if (length > 0) {
							// 如果知道响应的长度，调用publishProgress（）更新进度
							publishProgress((int) ((count / (float) length) * 100));
						}

						// 让线程休眠100ms
						Thread.sleep(100);
					}
					s = new String(baos.toByteArray());
					System.out.println(s);
				}
				// 返回结果
				return s;
			} catch (Exception e) {
				e.printStackTrace();

			}

			return null;
		}
	}
public void onResume() {
    super.onResume();
    MobclickAgent.onPageStart("MainScreen"); //统计页面
}
public void onPause() {
    super.onPause();
    MobclickAgent.onPageEnd("MainScreen"); 
}

}
	
