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
 * @author л����2013-10-22��дSchoolScoreFragment.java
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
		item1.put("s_yuwen", "���ģ�82");

		item1.put("s_math", "��ѧ��66");

	item1.put("s_english", "Ӣ�82");

		item1.put("s_total", "�ܷ֣�230");
		item1.put("s_order", "������4");
		item1.put("s_title", "��һ���¿�");
		data.add(item1);
		return data;
	}

	private List<Map<String, Object>> qizhong() {
		List<Map<String, Object>> data = null;
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("s_yuwen", "���ģ�82");

		item1.put("s_math", "��ѧ��71");

		item1.put("s_english", "Ӣ�78");

		item1.put("s_total", "�ܷ֣�231");
		item1.put("s_order", "������2");
		item1.put("s_title", "���п�");
		data.add(item1);
		return data;
	}

	private List<Map<String, Object>> getTesttwo() {
		List<Map<String, Object>> data = null;
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("s_yuwen", "���ģ�82");

		item1.put("s_math", "��ѧ��75");

		item1.put("s_english", "Ӣ�78");

		item1.put("s_total", "�ܷ֣�235");
		item1.put("s_order", "������4");
		item1.put("s_title", "�ڶ����¿�");
		data.add(item1);
		return data;
	}

	private List<Map<String, Object>> qimo() {
		List<Map<String, Object>> data = null;
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("s_yuwen", "���ģ�72");

		item1.put("s_math", "��ѧ��80");

		item1.put("s_english", "Ӣ�88");

		item1.put("s_total", "�ܷ֣�240");
		item1.put("s_order", "������2");
		item1.put("s_title", "��ĩ��");
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
	 * ʵ��AsyncTask�ж��������һ���򼸸����� ���� onPreExecute(), �÷�������ִ��ʵ�ʵĺ�̨����ǰ��UI
	 * thread���á������ڸ÷�������һЩ׼�����������ڽ�������ʾһ���������� ���� doInBackground(Params...),
	 * ����onPreExecute ����ִ�к�����ִ�У��÷��������ں�̨�߳��С����ｫ��Ҫ����ִ����Щ�ܺ�ʱ�ĺ�̨���㹤�������Ե���
	 * publishProgress����������ʵʱ��������ȡ��÷����ǳ��󷽷����������ʵ�֡� ����
	 * onProgressUpdate(Progress...),��publishProgress���������ú�UI
	 * thread��������������Ӷ��ڽ�����չʾ����Ľ�չ���������ͨ��һ������������չʾ�� ���� onPostExecute(Result),
	 * ��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã���̨�ļ�������ͨ���÷������ݵ�UI
	 * thread.
	 */

	private class AsyncCenter extends AsyncTask<String, Integer, String> {

		ProgressDialog pdialog;

		/*
		 * Ϊ����ȷ��ʹ��AsyncTask�࣬�����Ǽ����������ص�׼�� ����1) Task��ʵ��������UI thread�д��� ����2)
		 * execute����������UI thread�е��� ����3) ��Ҫ�ֶ��ĵ���onPreExecute(),
		 * onPostExecute(Result)��doInBackground(Params...),
		 * onProgressUpdate(Progress...)�⼸������ ����4) ��taskֻ�ܱ�ִ��һ�Σ������ε���ʱ��������쳣
		 * doInBackground������onPostExecute�Ĳ��������Ӧ
		 * ��������������AsyncTask�����ķ��Ͳ����б���ָ������һ��ΪdoInBackground���ܵĲ���
		 * ���ڶ���Ϊ��ʾ���ȵĲ������ڵ�����ΪdoInBackground���غ�onPostExecute����Ĳ�����
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
				// params[0]�������ӵ�url
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
							// ���֪����Ӧ�ĳ��ȣ�����publishProgress�������½���
							publishProgress((int) ((count / (float) length) * 100));
						}

						// ���߳�����100ms
						Thread.sleep(100);
					}
					s = new String(baos.toByteArray());
				}
				// ���ؽ��
				return s;
			} catch (Exception e) {
				e.printStackTrace();

			}

			return null;

		}
	}
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart("MainScreen"); //ͳ��ҳ��
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}

}