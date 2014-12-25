package app.main.ui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import android.support.v4.app.FragmentTransaction;
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
import app.main.pojo.HomeWork;
import app.main.util.HttpUtil;
import app.main.util.JsonUtils;

public class SchoolTaskFrament extends ListFragment {
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	List<Map<String, Object>> data;
	private String[] from;
	private TextView task_addTime;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int[] to;
	private String url = "http://192.168.191.1:8080/dwzStruts/dwz/gethomeWorksAction.action";
	private SimpleAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.school_task, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage = (TextView) mView.findViewById(R.id.tv_mainpage);

		tv_mainpage.setText(getResources().getString(R.string.school_tasks));

		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		task_addTime = (TextView) getActivity().findViewById(R.id.task_addTime);
		from = new String[] { "t_courseName", "t_content", "t_finishdate",
				"t_createdate" };
		to = new int[] { R.id.t_coursename, R.id.t_content, R.id.t_finishdate,
				R.id.t_createDate };
		AsyncCenter ac = new AsyncCenter(getActivity(), sdf.format(new Date()));
		ac.execute(url);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d("----->", position + "");
		TaskDetailFragment tdf = new TaskDetailFragment();
		MainActivity mainActivity = (MainActivity) getActivity();
		mainActivity.changeLeftFragment(tdf);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, R.string.today);
		menu.add(Menu.NONE, Menu.FIRST + 2, 1, R.string.yestoday);
		menu.add(Menu.NONE, Menu.FIRST + 3, 1, R.string.history);
	}

	public void dealData(String url, String Date) {
		data = new ArrayList<Map<String, Object>>();
		JsonUtils json = new JsonUtils();
		List<HomeWork> list = json.parseTask(HttpUtil.queryForPost(url, Date));

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			HomeWork homeWork = (HomeWork) iterator.next();
			Map<String, Object> item2 = new HashMap<String, Object>();

			item2.put("t_courseName", "�γ�����" + homeWork.getCoursename());
			item2.put("t_content", "��ҵ���ݣ�" + homeWork.getCoursecontent());
			item2.put("t_finishdate", "��ҵ���ʱ�䣺" + homeWork.getFinishtime());
			item2.put("t_createdate", "����ʱ�䣺" + homeWork.getCreateDate());
			data.add(item2);
		}
		if (data.size() > 0) {
			adapter = new SimpleAdapter(getActivity(), data,
					R.layout.course_task, from, to);
		}

		setListAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case Menu.FIRST + 1:

			AsyncCenter ac = new AsyncCenter(getActivity(),
					sdf.format(new Date()));
			ac.execute(url);
			task_addTime.setText(sdf.format(new Date()));
			return true;
		case Menu.FIRST + 2:
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			date = calendar.getTime();
			new AsyncCenter(getActivity(), sdf.format(date)).execute(url);
			task_addTime.setText(sdf.format(date));
			return true;
		case Menu.FIRST + 3:
			new AsyncCenter(getActivity(), null).execute(url);
			task_addTime.setText("��ҵ��ʷ��¼");
			return true;
		}
		return false;
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
		String createData;

		/*
		 * Ϊ����ȷ��ʹ��AsyncTask�࣬�����Ǽ����������ص�׼�� ����1) Task��ʵ��������UI thread�д��� ����2)
		 * execute����������UI thread�е��� ����3) ��Ҫ�ֶ��ĵ���onPreExecute(),
		 * onPostExecute(Result)��doInBackground(Params...),
		 * onProgressUpdate(Progress...)�⼸������ ����4) ��taskֻ�ܱ�ִ��һ�Σ������ε���ʱ��������쳣
		 * doInBackground������onPostExecute�Ĳ��������Ӧ
		 * ��������������AsyncTask�����ķ��Ͳ����б���ָ������һ��ΪdoInBackground���ܵĲ���
		 * ���ڶ���Ϊ��ʾ���ȵĲ������ڵ�����ΪdoInBackground���غ�onPostExecute����Ĳ�����
		 */

		public AsyncCenter(Context context, String createDate) {
			pdialog = ProgressDialog.show(context, "���ڼ�������", "�����ĵȴ�");
			pdialog.setButton("cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int i) {
					dialog.cancel();
				}
			});
			this.createData = createDate;

			pdialog.setCancelable(true);
			pdialog.setMax(100);
			pdialog.incrementSecondaryProgressBy(10);
			pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pdialog.show();
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			/*
			 * item2.put("t_courseName", "��ɿα�p31�ڶ���"); item2.put("t_content",
			 * R.drawable.chinese); item2.put("t_finishdate", "2013-02-10");
			 * item2.put("t_createdate", "2013-02-10");
			 */
			pdialog.cancel();
			data = new ArrayList<Map<String, Object>>();
			JsonUtils json = new JsonUtils();
			List<HomeWork> list = null;
			if (result != null&&!result.equals("")) {
               Log.e("result", result);
				list = json.parseTask(result);
			} else {

				list = json
						.parseTask("[{'id':'12', 'coursename':'Ӣ��', 'coursecontent':'����unit3����','createDate':'2014-05-29 00:00:00.0','finishtime':'2014-05-29 00:00:00.0'},{'id':'123', 'coursename':'语文', 'coursecontent':'完成课本p23','createDate':'2014-05-29 00:00:00.0','finishtime':'2014-05-29 00:00:00.0'},{'id':'124', 'coursename':'21', 'coursecontent':'21','createDate':'2014-05-29 00:00:00.0','finishtime':'2014-05-29 00:00:00.0'},{'id':'125', 'coursename':'24', 'coursecontent':'21','createDate':'2014-05-29 00:00:00.0','finishtime':'2014-05-29 00:00:00.0'},{'id':'126', 'coursename':'27', 'coursecontent':'21','createDate':'2014-05-29 00:00:00.0','finishtime':'2014-05-29 00:00:00.0'}]");
			}
			for (Iterator<HomeWork> iterator = list.iterator(); iterator
					.hasNext();) {
				HomeWork homeWork = (HomeWork) iterator.next();
				Map<String, Object> item2 = new HashMap<String, Object>();

				item2.put("t_courseName", "�γ�����" + homeWork.getCoursename());
				item2.put("t_content", "��ҵ���ݣ�" + homeWork.getCoursecontent());
				item2.put("t_finishdate", "��ҵ���ʱ�䣺" + homeWork.getFinishtime());
				item2.put("t_createdate", "����ʱ�䣺" + homeWork.getCreateDate());
				data.add(item2);
			}

			SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
					R.layout.course_task, from, to);
			setListAdapter(adapter);

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
				if (createData != null) {
					param.add(new BasicNameValuePair("cd", createData));
				}

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
