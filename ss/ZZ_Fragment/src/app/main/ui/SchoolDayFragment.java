/**
 * 
 */
package app.main.ui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import app.main.jxt.R;

/**
 * @author 谢俊良2013-10-22编写SchoolDayFragment.java
 * 
 */
public class SchoolDayFragment extends ListFragment {
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	RatingBar ratingBar;

	MySimpleAdapter adapter;
	private TextView tv_daytime;
	public TextView list_content;
	private ArrayList<Map<String, Object>> data = null;
	private String[] from;
	private int[] to;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.day_list, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage = (TextView) mView.findViewById(R.id.tv_mainpage);

		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.setHasOptionsMenu(true);

		from = new String[] { "day_time", "day_title", "day_image",
				"day_content" };
		to = new int[] { R.id.day_time, R.id.day_title, R.id.day_image,
				R.id.day_content };
		adapter = new MySimpleAdapter(getActivity(), getToday(),
				R.layout.school_day, from, to);

		// ratingBar=(RatingBar) getActivity().findViewById(R.id.day_show);
		setListAdapter(adapter);

		tv_mainpage.setText(getResources().getString(R.string.school_today));

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
		// ratingBar.setOnRatingBarChangeListener(new OnRatingbarChangeImpl());
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d("----->", position + "");

	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "昨天");
		menu.add(Menu.NONE, Menu.FIRST + 2, 1, "今天");
		menu.add(Menu.NONE, Menu.FIRST + 3, 1, "历史");
	}

	private List<Map<String, Object>> getToday() {
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("day_time",
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		item2.put("day_title", getString(R.string.title1));
		item2.put("day_image", R.drawable.p2);
		item2.put("day_content", "表现良好");
		// item2.put("day_show", 4);

		data.add(item2);
		return data;
	}

	private List<Map<String, Object>> getlastday() {
		Calendar c = Calendar.getInstance();
		Date date = null;

		date = new Date();

		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("day_time", lastDay);
		item2.put("day_title", getString(R.string.title1));
		item2.put("day_image", R.drawable.p1);
		item2.put("day_content", "表现良好");
		// item2.put("day_show", 4);
		data = new ArrayList<Map<String, Object>>();

		data.add(item2);
		return data;
	}

	private List<Map<String, Object>> getHistory() {
		Calendar c = Calendar.getInstance();
		Date date = null;

		date = new Date();

		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("day_title", getString(R.string.title1));
		item1.put("day_image", R.drawable.p1);
		item1.put("day_content", "表现优秀");
		item1.put("day_time", lastDay);
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("day_title", getString(R.string.title1));
		item2.put("day_image", R.drawable.p2);
		item2.put("day_content", "表现良好");
		item2.put("day_time",
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		// item2.put("day_show", 4);
		data = new ArrayList<Map<String, Object>>();

		data.add(item1);
		data.add(item2);
		return data;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			adapter = new MySimpleAdapter(getActivity(), getlastday(),
					R.layout.school_day, from, to);

			// ratingBar=(RatingBar) getActivity().findViewById(R.id.day_show);
			setListAdapter(adapter);

			return true;
		case Menu.FIRST + 2:
			adapter = new MySimpleAdapter(getActivity(), getToday(),
					R.layout.school_day, from, to);

			// ratingBar=(RatingBar) getActivity().findViewById(R.id.day_show);
			setListAdapter(adapter);

			return true;
		case Menu.FIRST + 3:
			adapter = new MySimpleAdapter(getActivity(), getHistory(),
					R.layout.school_day, from, to);

			// ratingBar=(RatingBar) getActivity().findViewById(R.id.day_show);
			setListAdapter(adapter);

			return true;
		}
		return false;
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

	// 评分组件的监听
	private class OnRatingbarChangeImpl implements OnRatingBarChangeListener {

		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			// TODO Auto-generated method stub
			int num = (int) rating;
			String result = null;
			switch (num) {
			case 5:
				result = "表现优秀";
				Toast.makeText(getActivity(), "表现优秀", Toast.LENGTH_SHORT)
						.show();
				list_content.setText(result);
				break;
			case 4:
				result = "表现良好";
				break;
			case 3:
				result = "表现一般";
				break;
			case 2:
				result = "表现不及格";
				break;
			case 1:
				result = "表现很差";
				break;
			default:
				result = "表现极差，家长去学校";
				break;
			}

		}

	}

	class MySimpleAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private List<Map<String, Object>> list;
		private int layoutID;
		private String flag[];
		private int itemIDs[];

		public MySimpleAdapter(Context context, List<Map<String, Object>> list,
				int layoutID, String[] flag, int[] itemIDs) {
			this.mInflater = LayoutInflater.from(context);
			this.list = list;
			this.layoutID = layoutID;
			this.flag = flag;
			this.itemIDs = itemIDs;

		}

		public int getCount() {
			return list.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = mInflater.inflate(layoutID, null);

			addListener(convertView);// 添加监听事件
			list_content = (TextView) convertView
					.findViewById(R.id.day_content);
			return convertView;
		}

		public void addListener(View convertView) { // 实现OnClick()方法
			ratingBar = (RatingBar) convertView.findViewById(R.id.day_show);
			list_content = (TextView) convertView
					.findViewById(R.id.day_content);
			ratingBar.setOnRatingBarChangeListener(new OnRatingbarChangeImpl());
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