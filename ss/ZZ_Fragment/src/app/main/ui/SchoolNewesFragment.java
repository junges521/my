package app.main.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import app.main.broad.RefreshLayout;
import app.main.broad.RefreshLayout.onRefreshListener;
import app.main.jxt.R;

/**
 * @author л����2013-11-4��дSchoolNewesFragment.java
 * �����첽ˢ��Ч����δ��ȫʵ��
 *
 */
public class SchoolNewesFragment extends ListFragment {
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	RefreshLayout refreshableView;

	/*
	Handler handler = new Handler() {
		// ��������
		MainActivity mainActivity=(MainActivity) getActivity();
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			//����������������ȡ������ʹ������������View
			SchoolNewesFragment.this.setListAdapter(new ContactAdapter(getActivity(),
					(List<News>) msg.obj, R.layout.textlist, mainActivity.getCache()));
		};
	};*/

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.list, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage=(TextView) mView.findViewById(R.id.tv_mainpage);
		return mView;
	}

	public List<Map<String, Object>> getListNews(){
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("list_title", getString(R.string.title1));
		item1.put("list_image", R.drawable.p1);
		item1.put("list_contect", getString(R.string.test));
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("list_title", getString(R.string.title2));
		item2.put("list_image", R.drawable.p2);
		item2.put("list_contect", getString(R.string.test));
		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("list_title", getString(R.string.title3));
		item3.put("list_image", R.drawable.p3);
		item3.put("list_contect", getString(R.string.test));
		Map<String, Object> item4 = new HashMap<String, Object>();
		item4.put("list_title", getString(R.string.title4));
		item4.put("list_image", R.drawable.p4);
		item4.put("list_contect", getString(R.string.test));
		Map<String, Object> item5 = new HashMap<String, Object>();
		item5.put("list_title", getString(R.string.title5));
		item5.put("list_image", R.drawable.p5);
		item5.put("list_contect", getString(R.string.test));
		Map<String, Object> item6 = new HashMap<String, Object>();
		item6.put("list_title", getString(R.string.title5));
		item6.put("list_image", R.drawable.p6);
		item6.put("list_contect", getString(R.string.test));
		Map<String, Object> item7 = new HashMap<String, Object>();
		item7.put("list_title", getString(R.string.title1));
		item7.put("list_image", R.drawable.p7);
		item7.put("list_contect", getString(R.string.test));
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(item1);
		data.add(item2);
		data.add(item3);
		data.add(item4);
		data.add(item5);
		data.add(item6);
		data.add(item1);
		return data;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.setHasOptionsMenu(true);
		refreshableView = (app.main.broad.RefreshLayout)getActivity(). findViewById(R.id.refreshable_view);
		
		String[] from = new String[] { "list_title", "list_image",
				"list_contect" };
		int[] to = new int[] { R.id.list_title, R.id.list_image,
				R.id.list_contect };
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), getListNews(),
				R.layout.textlist, from, to);
		setListAdapter(adapter);
		
		refreshableView.setOnRefreshListener(new onRefreshListener() {
			public void onRefresh() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);

		tv_mainpage.setText(getResources().getString(R.string.main_page));
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
		/*
		 * �������ŵļ���
		 */
/*		new Thread(new Runnable() {
			public void run() {
				try {
					//��ȡ��ϵ������
					List<Map<String, Object>> data= ContactService.getContacts();
					// ��Handler������Ϣ,����UI
					handler.sendMessage(handler.obtainMessage(8, data));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();*/
	}

	@Override
	public void onListItemClick(ListView listView, View v, int position, long id) {
		super.onListItemClick(listView, v, position, id);
	/*	Log.d("-----", position + "");
		Intent intent = new Intent(getActivity(), NewsFragment.class);
		startActivity(intent);*/
        HashMap<String, Object> map=(HashMap<String, Object>) listView.getItemAtPosition(position);
        String list_title=map.get("list_title").toString();
        Bundle b=new Bundle();
        b.putString("list_title", list_title);
		NewsFragment newsFragment=NewsFragment.newInstance(b);
		MainActivity mainActivity=(MainActivity) getActivity();
		mainActivity.changeFragment(newsFragment);
	}
	/*
	 * ʵ��AsyncTask�ж��������һ���򼸸����� 
����   onPreExecute(), �÷�������ִ��ʵ�ʵĺ�̨����ǰ��UI thread���á������ڸ÷�������һЩ׼�����������ڽ�������ʾһ���������� 
����  doInBackground(Params...), ����onPreExecute ����ִ�к�����ִ�У��÷��������ں�̨�߳��С����ｫ��Ҫ����ִ����Щ�ܺ�ʱ�ĺ�̨���㹤�������Ե��� publishProgress����������ʵʱ��������ȡ��÷����ǳ��󷽷����������ʵ�֡� 
����  onProgressUpdate(Progress...),��publishProgress���������ú�UI thread��������������Ӷ��ڽ�����չʾ����Ľ�չ���������ͨ��һ������������չʾ�� 
����  onPostExecute(Result), ��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã���̨�ļ�������ͨ���÷������ݵ�UI thread. 

	 */

	
/*	private class AsyncCenter extends AsyncTask<String, Integer, String> {

		
		ProgressDialog pdialog;
		
		 * Ϊ����ȷ��ʹ��AsyncTask�࣬�����Ǽ����������ص�׼�� 
����1) Task��ʵ��������UI thread�д��� 
����2) execute����������UI thread�е��� 
����3) ��Ҫ�ֶ��ĵ���onPreExecute(), onPostExecute(Result)��doInBackground(Params...), onProgressUpdate(Progress...)�⼸������ 
����4) ��taskֻ�ܱ�ִ��һ�Σ������ε���ʱ��������쳣 
      doInBackground������onPostExecute�Ĳ��������Ӧ��������������AsyncTask�����ķ��Ͳ����б���ָ������һ��ΪdoInBackground���ܵĲ������ڶ���Ϊ��ʾ���ȵĲ������ڵ�����ΪdoInBackground���غ�onPostExecute����Ĳ�����
		 * 
		 
		
		
		public AsyncCenter(Context context) {
			pdialog = new ProgressDialog(context, 0);   
            pdialog.setButton("ȡ��", new DialogInterface.OnClickListener() {
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
					System.out.println(s);
				}
				// ���ؽ��
				return s;
			} catch (Exception e) {
				e.printStackTrace();

			}

			return null;
		}
	}*/
	
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart("MainScreen"); //ͳ��ҳ��
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}

}
