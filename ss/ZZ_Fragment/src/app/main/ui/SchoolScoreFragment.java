/**
 * 
 */
package app.main.ui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import app.main.jxt.R;

/**
 * @author 谢俊良2013-10-22编写SchoolScoreFragment.java
 * 
 */
public class SchoolScoreFragment extends ListFragment {
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	private SimpleAdapter adapter;
	private String[] from;

	private int[] to;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.school_score, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage = (TextView) mView.findViewById(R.id.tv_mainpage);
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.setHasOptionsMenu(true);

		from = new String[] { "s_yuwen", "s_math", "s_english", "s_total",
				"s_order","s_title" };
		to = new int[] { R.id.s_yuwen, R.id.s_math, R.id.s_english,
				R.id.s_total, R.id.s_order ,R.id.s_title};
		adapter = new SimpleAdapter(getActivity(), getTestOne(),
				R.layout.course_score, from, to);
		setListAdapter(adapter);

		tv_mainpage.setText(getResources().getString(R.string.school_score));

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
	}

	private List<Map<String, Object>> getTestOne() {
		List<Map<String, Object>> data = null;
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("s_yuwen", "语文：82");

		item1.put("s_math", "数学：66");

	item1.put("s_english", "英语：82");

		item1.put("s_total", "总分：230");
		item1.put("s_order", "排名：4");
		item1.put("s_title", "第一次月考");
		data.add(item1);
		return data;
	}

	private List<Map<String, Object>> qizhong() {
		List<Map<String, Object>> data = null;
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("s_yuwen", "语文：82");

		item1.put("s_math", "数学：71");

		item1.put("s_english", "英语：78");

		item1.put("s_total", "总分：231");
		item1.put("s_order", "排名：2");
		item1.put("s_title", "期中考");
		data.add(item1);
		return data;
	}

	private List<Map<String, Object>> getTesttwo() {
		List<Map<String, Object>> data = null;
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("s_yuwen", "语文：82");

		item1.put("s_math", "数学：75");

		item1.put("s_english", "英语：78");

		item1.put("s_total", "总分：235");
		item1.put("s_order", "排名：4");
		item1.put("s_title", "第二次月考");
		data.add(item1);
		return data;
	}

	private List<Map<String, Object>> qimo() {
		List<Map<String, Object>> data = null;
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("s_yuwen", "语文：72");

		item1.put("s_math", "数学：80");

		item1.put("s_english", "英语：88");

		item1.put("s_total", "总分：240");
		item1.put("s_order", "排名：2");
		item1.put("s_title", "期末考");
		data.add(item1);
		return data;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, R.string.test1);
		menu.add(Menu.NONE, Menu.FIRST + 2, 1, R.string.test2);
		menu.add(Menu.NONE, Menu.FIRST + 3, 1, R.string.test3);
		menu.add(Menu.NONE, Menu.FIRST + 4, 1, R.string.test4);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			adapter = new SimpleAdapter(getActivity(), getTestOne(),
					R.layout.course_score, from, to);
			setListAdapter(adapter);
			return true;
		case Menu.FIRST + 2:
			adapter = new SimpleAdapter(getActivity(), qizhong(),
					R.layout.course_score, from, to);
			setListAdapter(adapter);

			return true;
		case Menu.FIRST + 3:
			adapter = new SimpleAdapter(getActivity(), getTesttwo(),
					R.layout.course_score, from, to);
			setListAdapter(adapter);
			return true;
		case Menu.FIRST + 4:
			adapter = new SimpleAdapter(getActivity(), qimo(),
					R.layout.course_score, from, to);
			setListAdapter(adapter);
			return true;
		}
		return false;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d("----->", position + "");

	}

	/*
	 * 实现AsyncTask中定义的下面一个或几个方法 　　 onPreExecute(), 该方法将在执行实际的后台操作前被UI
	 * thread调用。可以在该方法中做一些准备工作，如在界面上显示一个进度条。 　　 doInBackground(Params...),
	 * 将在onPreExecute 方法执行后马上执行，该方法运行在后台线程中。这里将主要负责执行那些很耗时的后台计算工作。可以调用
	 * publishProgress方法来更新实时的任务进度。该方法是抽象方法，子类必须实现。 　　
	 * onProgressUpdate(Progress...),在publishProgress方法被调用后，UI
	 * thread将调用这个方法从而在界面上展示任务的进展情况，例如通过一个进度条进行展示。 　　 onPostExecute(Result),
	 * 在doInBackground 执行完成后，onPostExecute 方法将被UI thread调用，后台的计算结果将通过该方法传递到UI
	 * thread.
	 */

	private class AsyncCenter extends AsyncTask<String, Integer, String> {

		ProgressDialog pdialog;

		/*
		 * 为了正确的使用AsyncTask类，以下是几条必须遵守的准则： 　　1) Task的实例必须在UI thread中创建 　　2)
		 * execute方法必须在UI thread中调用 　　3) 不要手动的调用onPreExecute(),
		 * onPostExecute(Result)，doInBackground(Params...),
		 * onProgressUpdate(Progress...)这几个方法 　　4) 该task只能被执行一次，否则多次调用时将会出现异常
		 * doInBackground方法和onPostExecute的参数必须对应
		 * ，这两个参数在AsyncTask声明的泛型参数列表中指定，第一个为doInBackground接受的参数
		 * ，第二个为显示进度的参数，第第三个为doInBackground返回和onPostExecute传入的参数。
		 */

		public AsyncCenter(Context context) {
			pdialog = new ProgressDialog(context, 0);
			pdialog.setButton("cancel", new DialogInterface.OnClickListener() {
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
			pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pdialog.show();
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
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
			try {

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
